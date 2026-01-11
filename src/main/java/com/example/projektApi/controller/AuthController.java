package com.example.projektApi.controller;


import com.example.projektApi.model.User;
import com.example.projektApi.repository.UserRepository;
import com.example.projektApi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        String token = authService.login(user.getUsername(), user.getPassword());
        User loggedInUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        return ResponseEntity.ok(Map.of("token", token,"user", loggedInUser));
    }
}