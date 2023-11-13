package com.microcommerce.userservice.data.dto;

import com.microcommerce.common.validation.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email can't be blank or empty")
    private String email;

    @NotBlank(message = "Password can't be blank or empty")
    private String password;
}
