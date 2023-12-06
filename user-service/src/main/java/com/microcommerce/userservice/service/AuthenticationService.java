package com.microcommerce.userservice.service;

import com.microcommerce.common.validation.*;
import com.microcommerce.common.web.ApiResponse;
import com.microcommerce.userservice.data.dto.LoginRequest;
import com.microcommerce.userservice.data.dto.RegistrationRequest;
import com.microcommerce.userservice.data.entity.ConfirmationToken;
import com.microcommerce.userservice.data.entity.Role;
import com.microcommerce.userservice.data.entity.User;
import com.microcommerce.userservice.data.enums.Authority;
import com.microcommerce.userservice.data.event.AccountCreatedEvent;
import com.microcommerce.userservice.repository.RoleRepository;
import com.microcommerce.userservice.repository.UserRepository;
import com.microcommerce.userservice.util.validation.EmailFormatValidationRule;
import com.microcommerce.userservice.util.validation.EmailUniqueValidationRule;
import com.microcommerce.userservice.util.validation.PasswordValidationRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;
    private final KafkaTemplate<UUID, AccountCreatedEvent> kafkaTemplate;

    private final ConstraintValidationRule<RegistrationRequest> registrationRequestConstraintValidationRule;
    private final ConstraintValidationRule<LoginRequest> loginRequestConstraintValidationRule;
    private final EmailUniqueValidationRule emailUniqueValidationRule;
    private final EmailFormatValidationRule emailFormatValidationRule;
    private final PasswordValidationRule passwordValidationRule;

    @Transactional
    public ApiResponse<Void> registerUser(RegistrationRequest registrationRequest) {
        ValidationResult validationResult = Validator.<RegistrationRequest>builder()
                .addRule(registrationRequestConstraintValidationRule)
                .addRule(emailFormatValidationRule, true)
                .addRule(passwordValidationRule, true)
                .addRule(emailUniqueValidationRule, true)
                .build()
                .validateRules(registrationRequest);

        if (validationResult.isNotValid()) {
            return ApiResponse.<Void>builder()
                    .badRequest(validationResult.getMessages())
                    .build();
        }

        Set<Role> authorities = new HashSet<>() {{
            add(roleRepository.findByAuthority(Authority.USER).orElseThrow(NoSuchElementException::new));
        }};

        var confirmationToken = new ConfirmationToken();
        var user = User.builder()
                .email(registrationRequest.getEmail())
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .isEnabled(false)
                .authorities(authorities)
                .confirmationToken(confirmationToken)
                .build();

        var savedUser = userRepository.save(user);
        produceAccountCreatedEvent(savedUser);

        return ApiResponse.<Void>builder()
                .successfulOperation()
                .build();
    }

    private void produceAccountCreatedEvent(User user) {
        var accountCreatedEvent = AccountCreatedEvent.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .activationToken(user.getConfirmationToken().getTokenId().toString())
                .build();

        var completableFuture = kafkaTemplate.send("account-topic", UUID.randomUUID(), accountCreatedEvent);

        completableFuture.whenComplete((result, exception) -> {
            if (exception != null) {
                completableFuture.completeExceptionally(exception);
            } else {
                completableFuture.complete(result);
            }
        });
    }

    /**
     * Logs in a user by validating the provided login request, and if successful,
     * generates a JWT token for authentication.
     *
     * @param loginRequest The login request containing user credentials.
     * @return An {@link ApiResponse} with a JWT token if login is successful or
     *         an error message if the login credentials are invalid or the account
     *         is disabled.
     */
    public ApiResponse<String> loginUser(LoginRequest loginRequest) {
        var constraintValidationResult = validateLoginRequest(loginRequest, loginRequestConstraintValidationRule);

        if (constraintValidationResult.isNotValid()) {
            return ApiResponse.<String>builder().badRequest(constraintValidationResult.getMessages()).build();
        }

        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            var authentication = authenticationManager.authenticate(authenticationToken);
            var user = (User) authentication.getPrincipal();

            String jwtToken = jwtGenerator.generateJwt(authentication, String.valueOf(user.getUserId()));

            return ApiResponse.<String>builder()
                    .successfulOperation(jwtToken)
                    .build();
        } catch (BadCredentialsException ex) {
            return ApiResponse.<String>builder()
                    .badRequest("Invalid credentials", "Invalid login credentials. Please check your username and password and try again.")
                    .build();
        } catch (DisabledException ex) {
            return ApiResponse.<String>builder()
                    .badRequest("Account disabled", "This account is not enabled yet, please check your email for instructions.")
                    .build();
        }
    }

    private ValidationResult validateLoginRequest(LoginRequest loginRequest, ValidationRule<LoginRequest> validationRule) {
        return new Validator<LoginRequest>().validateRule(loginRequest, validationRule);
    }

    @Transactional
    public ApiResponse<String> confirmAccount(String token) {
        var user = userRepository.findUserByConfirmationToken(UUID.fromString(token));
        var confirmationToken = user.getConfirmationToken();

        if (confirmationToken.isExpired() || confirmationToken.isUsed()) {
            log.info("Account confirmation token expired or already used.");
            return ApiResponse.<String>builder()
                    .badRequest("Token expired or used", "Account confirmation token is expired or already used.")
                    .build();
        }

        confirmationToken.setUsed(true);
        user.setEnabled(true);
        userRepository.save(user);

        log.info("User account confirmed. User: {} isEnabled: {}. Token used: {}", user.getUserId(), user.isEnabled(), confirmationToken.isUsed());

        return ApiResponse.<String>builder()
                .successfulOperation(String.format("Account %s confirmed", user.getEmail()))
                .build();
    }
}
