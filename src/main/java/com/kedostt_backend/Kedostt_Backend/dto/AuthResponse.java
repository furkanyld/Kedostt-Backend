package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {

    private String token;

    private String username;

    private String role;
}
