package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalRequest {
    private String name;
    private String species;
    private String breed;
    private int age;
    private String gender;
    private String description;
    private String imageUrl;
}
