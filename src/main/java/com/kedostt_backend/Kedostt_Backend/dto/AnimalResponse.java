package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalResponse {

    private Long id;

    private String name;

    private String species;

    private String breed;

    private int ageYears;

    private int ageMonths;

    private String gender;

    private String description;

    private List<String> imageUrls;

    private String videoUrl;

    private boolean adopted;

    private boolean isVisible;
}