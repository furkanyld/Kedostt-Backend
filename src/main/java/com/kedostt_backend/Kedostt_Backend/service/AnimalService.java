package com.kedostt_backend.Kedostt_Backend.service;

import com.kedostt_backend.Kedostt_Backend.dto.AnimalRequest;
import com.kedostt_backend.Kedostt_Backend.dto.AnimalResponse;
import java.util.List;

public interface AnimalService {
    AnimalResponse  createAnimal(AnimalRequest animalRequest );
    List<AnimalResponse> getAllAnimals();
    AnimalResponse getAnimalById(Long id);
    AnimalResponse updateAnimal(Long id, AnimalRequest request);
    void deleteAnimal(Long id);
}
