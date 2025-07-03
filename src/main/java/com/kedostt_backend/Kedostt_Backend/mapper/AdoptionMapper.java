package com.kedostt_backend.Kedostt_Backend.mapper;

import com.kedostt_backend.Kedostt_Backend.dto.AdoptionDto;
import com.kedostt_backend.Kedostt_Backend.model.Adoption;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AdoptionMapper {

    public Adoption toEntity(AdoptionDto dto, Animal animal) {
        return Adoption.builder()
                .fullName(dto.getFullName())
                .age(dto.getAge())
                .occupation(dto.getOccupation())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .note(dto.getNote())
                .status(dto.getStatus() != null ? dto.getStatus() : "PENDING")
                .animal(animal)
                .build();
    }

    public AdoptionDto toDto(Adoption adoption) {
        if (adoption.getAnimal() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Animal bilgisi bulunamadı.");
        }
        return AdoptionDto.builder()
                .id(adoption.getId())
                .fullName(adoption.getFullName())
                .age(adoption.getAge())
                .occupation(adoption.getOccupation())
                .phoneNumber(adoption.getPhoneNumber())
                .email(adoption.getEmail())
                .status(adoption.getStatus())
                .note(adoption.getNote())
                .createdAt(adoption.getCreatedAt())
                .username(adoption.getUser() != null ? adoption.getUser().getUsername() : null)
                .animalId(adoption.getAnimal().getId())
                .animalName(adoption.getAnimal().getName())
                .build();
    }
}