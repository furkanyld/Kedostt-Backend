package com.kedostt_backend.Kedostt_Backend.mapper;

import com.kedostt_backend.Kedostt_Backend.dto.AdoptionDto;
import com.kedostt_backend.Kedostt_Backend.model.Adoption;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import org.springframework.stereotype.Component;

@Component
public class AdoptionMapper {

    public Adoption toEntity(AdoptionDto dto, Animal animal) {
        Adoption adoption = new Adoption();
        adoption.setFullName(dto.getFullName());
        adoption.setAge(dto.getAge());
        adoption.setOccupation(dto.getOccupation());
        adoption.setPhoneNumber(dto.getPhoneNumber());
        adoption.setEmail(dto.getEmail());
        adoption.setNote(dto.getNote());
        adoption.setAnimal(animal);
        return adoption;
    }

    public AdoptionDto toDto(Adoption adoption) {
        AdoptionDto dto = new AdoptionDto();
        dto.setId(adoption.getId());
        dto.setFullName(adoption.getFullName());
        dto.setAge(adoption.getAge());
        dto.setOccupation(adoption.getOccupation());
        dto.setPhoneNumber(adoption.getPhoneNumber());
        dto.setEmail(adoption.getEmail());
        dto.setNote(adoption.getNote());
        dto.setAnimalId(adoption.getAnimal().getId());
        return dto;
    }
}