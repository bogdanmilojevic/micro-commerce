package com.microcommerce.userservice.controller;

import com.microcommerce.common.web.ApiResponse;
import com.microcommerce.userservice.data.dto.LoginRequest;
import com.microcommerce.userservice.data.dto.RegistrationRequest;
import com.microcommerce.userservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        var response = authenticationService.registerUser(registrationRequest);
        var httpStatus = response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody LoginRequest loginRequest) {
        var response = authenticationService.loginUser(loginRequest);
        var httpStatus = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(response);
    }

    @GetMapping("/confirm")
    public ResponseEntity<ApiResponse<String>> confirmAccount(@RequestParam(name = "token") String token) {
        var response = authenticationService.confirmAccount(token);
        var httpStatus = response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(httpStatus).body(response);
    }
}
