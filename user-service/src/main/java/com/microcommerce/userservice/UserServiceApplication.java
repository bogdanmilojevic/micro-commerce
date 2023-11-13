package com.microcommerce.userservice;

import com.microcommerce.userservice.data.entity.Role;
import com.microcommerce.userservice.data.entity.User;
import com.microcommerce.userservice.data.enums.Authority;
import com.microcommerce.userservice.repository.RoleRepository;
import com.microcommerce.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (roleRepository.findByAuthority(Authority.ADMIN).isPresent()) return;

            Role adminRole = roleRepository.save(new Role(Authority.ADMIN.name()));
            Role userRole = roleRepository.save(new Role(Authority.USER.name()));

            Set<Role> adminAuthorities = new HashSet<>();
            adminAuthorities.add(adminRole);

            User administrator = User.builder()
                    .email("admin@mail.com")
                    .dateOfBirth(OffsetDateTime.now())
                    .isEnabled(true)
                    .firstName("Admin")
                    .lastName("Admin")
                    .authorities(adminAuthorities)
                    .password(passwordEncoder.encode("password"))
                    .build();

            log.info("Admin with email '{}' saved", administrator.getEmail());

            userRepository.save(administrator);
        };
    }
}
