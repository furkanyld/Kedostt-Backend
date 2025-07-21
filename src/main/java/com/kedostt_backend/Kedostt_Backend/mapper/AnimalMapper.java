package com.kedostt_backend.Kedostt_Backend.mapper;

import com.kedostt_backend.Kedostt_Backend.dto.AnimalResponse;
import com.kedostt_backend.Kedostt_Backend.dto.AnimalRequest;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    public Animal toEntity(AnimalRequest request) {
        return Animal.builder()
                .name(request.getName())
                .species(request.getSpecies())
                .breed(request.getBreed())
                .ageYears(request.getAgeYears())
                .ageMonths(request.getAgeMonths())
                .gender(request.getGender())
                .description(request.getDescription())
                .imageUrls(request.getImageUrls())
                .videoUrl(request.getVideoUrl())
                .isVisible(request.isVisible())
                .build();
    }

    public AnimalResponse toDto(Animal animal) {
        return AnimalResponse.builder()
                .id(animal.getId())
                .name(animal.getName())
                .species(animal.getSpecies())
                .breed(animal.getBreed())
                .ageYears(animal.getAgeYears())
                .ageMonths(animal.getAgeMonths())
                .gender(animal.getGender())
                .description(animal.getDescription())
                .imageUrls(animal.getImageUrls())
                .videoUrl(animal.getVideoUrl())
                .adopted(animal.isAdopted())
                .isVisible(animal.isVisible())
                .build();
    }

    public void updateEntity(Animal animal, AnimalRequest request) {
        animal.setName(request.getName());
        animal.setSpecies(request.getSpecies());
        animal.setBreed(request.getBreed());
        animal.setAgeYears(request.getAgeYears());
        animal.setAgeMonths(request.getAgeMonths());
        animal.setGender(request.getGender());
        animal.setDescription(request.getDescription());
        animal.setImageUrls(request.getImageUrls());
        animal.setVideoUrl(request.getVideoUrl());
        animal.setVisible(request.isVisible());
    }
}
