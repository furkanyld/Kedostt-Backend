package com.kedostt_backend.Kedostt_Backend.impl;

import com.kedostt_backend.Kedostt_Backend.dto.AnimalRequest;
import com.kedostt_backend.Kedostt_Backend.dto.AnimalResponse;
import com.kedostt_backend.Kedostt_Backend.mapper.AnimalMapper;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.repository.AnimalRepository;
import com.kedostt_backend.Kedostt_Backend.service.AnimalService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    private final AnimalMapper animalMapper;

    @Override
    public AnimalResponse createAnimal(AnimalRequest request){
        Animal animal = animalMapper.toEntity(request);
        Animal saved = animalRepository.save(animal);
        return animalMapper.toDto(saved);
    }

    @Override
    public List<AnimalResponse> getAllAnimals(){
        return animalRepository.findAll().stream()
                .map(animalMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnimalResponse getAnimalById(Long id){
        Animal animal = animalRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Hayvan Bulunamadı: "+id));
        return animalMapper.toDto(animal);
    }

    @Override
    public AnimalResponse updateAnimal(Long id, AnimalRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı!"));

        animal.setName(request.getName());
        animal.setDescription(request.getDescription());
        animal.setImageUrl(request.getImageUrl());

        Animal saved = animalRepository.save(animal);
        return animalMapper.toDto(saved);
    }

    @Override
    public void toggleVisibility(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));
        animal.setVisible(!animal.isVisible());
        animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Silinecek hayvan bulunamadı: " + id);
        }
        animalRepository.deleteById(id);
    }
}
