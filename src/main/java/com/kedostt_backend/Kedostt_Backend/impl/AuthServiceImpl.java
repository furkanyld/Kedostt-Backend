package com.kedostt_backend.Kedostt_Backend.impl;

import com.kedostt_backend.Kedostt_Backend.dto.AuthResponse;
import com.kedostt_backend.Kedostt_Backend.dto.LoginRequest;
import com.kedostt_backend.Kedostt_Backend.security.JwtTokenProvider;
import com.kedostt_backend.Kedostt_Backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse login(LoginRequest loginRequest){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            String token = jwtTokenProvider.generateToken(authentication);
            String username = authentication.getName();
            String role = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("USER");

            return AuthResponse.builder().token(token)
                    .username(username)
                    .role(role)
                    .build();
        } catch (AuthenticationException e) {
            throw new RuntimeException("Geçersiz kullanıcı adı ya da şifre!");
        }
    }
}
