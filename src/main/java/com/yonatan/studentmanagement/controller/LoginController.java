package com.yonatan.studentmanagement.controller;

import com.yonatan.studentmanagement.dto.LoginRequest;
import com.yonatan.studentmanagement.dto.LoginResponse;
import com.yonatan.studentmanagement.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginController(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

        String username = authentication.getName();

        String role = authentication.getAuthorities()
                .iterator()
                .next()
                .getAuthority();

        role = role.replace("ROLE_", "");

        String token = jwtService.generateToken(username, role);

        return new LoginResponse(
                "Login successful",
                username,
                role,
                token
        );
    }
}