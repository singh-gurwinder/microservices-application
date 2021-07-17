package com.sidhu.auth.controller;

import com.sidhu.auth.model.AuthRequest;
import com.sidhu.auth.model.AuthResponse;
import com.sidhu.auth.service.AuthService;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest)
            throws InvalidCredentialsException {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
