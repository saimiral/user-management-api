package com.saimiral.usermanagement.controller;

import com.saimiral.usermanagement.dto.AuthResponse;
import com.saimiral.usermanagement.dto.LoginRequest;
import com.saimiral.usermanagement.dto.RegisterRequest;
import com.saimiral.usermanagement.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request.getEmail(), request.getPassword());
    }
}