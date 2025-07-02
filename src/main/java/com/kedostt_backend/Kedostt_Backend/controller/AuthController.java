package com.kedostt_backend.Kedostt_Backend.controller;

import com.kedostt_backend.Kedostt_Backend.dto.AuthResponse;
import com.kedostt_backend.Kedostt_Backend.dto.LoginRequest;
import com.kedostt_backend.Kedostt_Backend.dto.RegisterRequest;
import com.kedostt_backend.Kedostt_Backend.dto.UserDto;
import com.kedostt_backend.Kedostt_Backend.security.JwtTokenProvider;
import com.kedostt_backend.Kedostt_Backend.service.AuthService;
import com.kedostt_backend.Kedostt_Backend.service.UserService;
import lombok.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @CrossOrigin(
            origins = {"https://www.kedostt.com.tr", "https://kedostt.com.tr"},
            allowCredentials = "true"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @CrossOrigin(
            origins = {"https://www.kedostt.com.tr", "https://kedostt.com.tr"},
            allowCredentials = "true"
    )
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest) {
        UserDto registeredUser = userService.registerUser(registerRequest);
        return ResponseEntity.ok(registeredUser);
    }
}
