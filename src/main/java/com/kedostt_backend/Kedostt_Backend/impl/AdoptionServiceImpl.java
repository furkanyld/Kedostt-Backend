package com.kedostt_backend.Kedostt_Backend.impl;

import com.kedostt_backend.Kedostt_Backend.dto.AdoptionDto;
import com.kedostt_backend.Kedostt_Backend.mapper.AdoptionMapper;
import com.kedostt_backend.Kedostt_Backend.model.Adoption;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.model.User;
import com.kedostt_backend.Kedostt_Backend.repository.AdoptionRepository;
import com.kedostt_backend.Kedostt_Backend.repository.AnimalRepository;
import com.kedostt_backend.Kedostt_Backend.repository.UserRepository;
import com.kedostt_backend.Kedostt_Backend.service.AdoptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final AnimalRepository animalRepository;
    private final UserRepository userRepository;
    private final AdoptionMapper adoptionMapper;

    @Override
    public AdoptionDto createAdoption(AdoptionDto dto, Authentication authentication) {
        Animal animal = animalRepository.findById(dto.getAnimalId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hayvan bulunamadı"));

        Adoption adoption = adoptionMapper.toEntity(dto, animal);

        if (authentication != null) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı!"));
            adoption.setUser(user);
        }

        return adoptionMapper.toDto(adoptionRepository.save(adoption));
    }

    @Override
    public List<AdoptionDto> getAllAdoptions() {
        return adoptionRepository.findAll()
                .stream()
                .map(adoptionMapper::toDto)
                .toList();
    }

    @Override
    public AdoptionDto updateAdoptionStatus(Long id, String status){
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Başvuru bulunamadı!"));
        adoption.setStatus(status);
        adoptionRepository.save(adoption);
        return adoptionMapper.toDto(adoption);
    }

    @Override
    public List<AdoptionDto> getAdoptionsByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Kullanıcı bulunamadı!"));

        return adoptionRepository.findAllByUser(user)
                .stream()
                .map(adoptionMapper::toDto)
                .toList();
    }

    @Override
    public void acceptAdoption(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId)
                .orElseThrow(() -> new RuntimeException("Adoption not found"));

        Animal animal = adoption.getAnimal();
        if (animal.isAdopted()) {
            throw new IllegalStateException("Hayvan zaten sahiplenilmiş.");
        }

        animal.setAdopted(true);
        animalRepository.save(animal);

        adoption.setStatus("ACCEPTED");
        adoptionRepository.save(adoption);
    }

    @Override
    public void rejectAdoption(Long adoptionId) {
        Adoption adoption = adoptionRepository.findById(adoptionId)
                .orElseThrow(() -> new RuntimeException("Adoption not found"));
        adoption.setStatus("REJECTED");
        adoptionRepository.save(adoption);
    }

    @Override
    public void deleteAdoption(Long id) {
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Başvuru bulunamadı!"));

        adoptionRepository.delete(adoption);
    }
}
