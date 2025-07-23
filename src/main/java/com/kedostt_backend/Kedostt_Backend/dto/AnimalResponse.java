package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<String> imageFileIds;

    private String videoUrl;
    private String videoFileId;

    private boolean adopted;
    private boolean isVisible;
}
