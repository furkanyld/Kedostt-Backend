package com.kedostt_backend.Kedostt_Backend.service;

import com.kedostt_backend.Kedostt_Backend.dto.AnimalRequest;
import com.kedostt_backend.Kedostt_Backend.dto.AnimalResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AnimalService {
    AnimalResponse  createAnimal(AnimalRequest animalRequest );
    List<AnimalResponse> getAllAnimals();
    AnimalResponse getAnimalById(Long id);
    AnimalResponse updateAnimal(Long id, AnimalRequest request);
    void toggleVisibility(Long animalId);
    void deleteAnimal(Long id);
    AnimalResponse createAnimalWithFiles(
            String name,
            String species,
            String breed,
            int ageYears,
            int ageMonths,
            String gender,
            String description,
            List<MultipartFile> images,
            MultipartFile video,
            boolean visible
    ) throws IOException;
    AnimalResponse updateAnimalWithFiles(
            Long id,
            String name,
            String species,
            String breed,
            int ageYears,
            int ageMonths,
            String gender,
            String description,
            List<String> existingImageUrls,
            List<MultipartFile> newImages,
            MultipartFile video,
            boolean visible,
            boolean deleteVideo
    ) throws IOException;
}
