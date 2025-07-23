package com.kedostt_backend.Kedostt_Backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalRequest {

    @NotBlank(message = "Ad boş bırakılamaz")
    private String name;

    @NotBlank(message = "Tür (cat/dog/bird vs.) boş bırakılamaz")
    private String species;

    private String breed;

    @Min(value = 0, message = "Yaş (yıl) negatif olamaz")
    private int ageYears;

    @Min(value = 0, message = "Yaş (ay) negatif olamaz")
    @Max(value = 11, message = "Ay 0 ile 11 arasında olmalıdır")
    private int ageMonths;

    private String gender;

    @NotBlank(message = "Açıklama girilmelidir")
    @Size(max = 500, message = "Açıklama en fazla 500 karakter olabilir")
    private String description;

    // Yeni alanlar:
    private List<String> imageUrls;
    private List<String> imageFileIds; // ✅ Eklemen gereken alan

    private String videoUrl;
    private String videoFileId;        // ✅ Eklemen gereken alan

    private boolean isVisible = true;

    public AnimalRequest(String name, String species, String breed, int ageYears, int ageMonths,
                         String gender, String description, boolean isVisible,
                         List<String> imageUrls, List<String> imageFileIds,
                         String videoUrl, String videoFileId) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.ageYears = ageYears;
        this.ageMonths = ageMonths;
        this.gender = gender;
        this.description = description;
        this.isVisible = isVisible;
        this.imageUrls = imageUrls;
        this.imageFileIds = imageFileIds;
        this.videoUrl = videoUrl;
        this.videoFileId = videoFileId;
    }
}

