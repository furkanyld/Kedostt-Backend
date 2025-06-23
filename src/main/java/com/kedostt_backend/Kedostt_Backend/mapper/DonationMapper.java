package com.kedostt_backend.Kedostt_Backend.mapper;

import com.kedostt_backend.Kedostt_Backend.dto.DonationDto;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.model.Donation;
import org.springframework.stereotype.Component;

@Component
public class DonationMapper {

    public Donation toEntity(DonationDto donationDto, Animal animal) {
        Donation donation = new Donation();
        donation.setAmount(donationDto.getAmount());
        donation.setDonorName(donationDto.getDonorName());
        donation.setMessage(donationDto.getMessage());
        donation.setAnimal(animal);
        return donation;
    }

    public DonationDto toDto(Donation donation) {
        DonationDto dto = new DonationDto();
        dto.setId(donation.getId());
        dto.setDonorName(donation.getDonorName());
        dto.setAmount(donation.getAmount());
        dto.setMessage(donation.getMessage());
        dto.setAnimalId(donation.getAnimal().getId());
        dto.setCreatedAt(donation.getCreatedAt());
        return dto;
    }
}