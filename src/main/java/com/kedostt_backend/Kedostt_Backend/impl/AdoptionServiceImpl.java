package com.kedostt_backend.Kedostt_Backend.impl;

import com.kedostt_backend.Kedostt_Backend.dto.AdoptionDto;
import com.kedostt_backend.Kedostt_Backend.mapper.AdoptionMapper;
import com.kedostt_backend.Kedostt_Backend.model.Adoption;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.repository.AdoptionRepository;
import com.kedostt_backend.Kedostt_Backend.repository.AnimalRepository;
import com.kedostt_backend.Kedostt_Backend.service.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final AnimalRepository animalRepository;
    private final AdoptionMapper adoptionMapper;

    @Override
    public AdoptionDto createAdoption(AdoptionDto dto) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı!"));

        Adoption adoption = adoptionMapper.toEntity(dto, animal);
        return adoptionMapper.toDto(adoptionRepository.save(adoption));
    }

    @Override
    public List<AdoptionDto> getAllAdoptions() {
        return adoptionRepository.findAll()
                .stream()
                .map(adoptionMapper::toDto)
                .toList();
    }
}
