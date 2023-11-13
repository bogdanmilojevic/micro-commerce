package com.microcommerce.userservice.data.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Email can't be null or empty")
    private String email;

    @NotBlank(message = "Password can't be null or empty")
    private String password;

    @NotBlank(message = "First name can't be null or empty")
    @Length(max = 100, message = "First name can't be longer than 100 characters.")
    private String firstName;

    @NotBlank(message = "Last name can't be null or empty")
    @Length(max = 100, message = "Last name can't be longer than 100 characters.")
    private String lastName;

    @NotNull(message = "Date of birth can't be null or empty")
    private OffsetDateTime dateOfBirth;
}
