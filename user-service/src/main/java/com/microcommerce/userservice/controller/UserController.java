package com.microcommerce.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/{user-id}")
    public ResponseEntity<?> getUser(@PathVariable("user-id") String userId) {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<?>> getAllUsers() {
        return null;
    }
}
