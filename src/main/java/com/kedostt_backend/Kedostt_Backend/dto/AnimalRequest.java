package com.kedostt_backend.Kedostt_Backend.dto;

import lombok.*;
import jakarta.validation.constraints.*;

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

    private List<String> imageUrls;

    private String videoUrl;

    private boolean isVisible = true;
}
