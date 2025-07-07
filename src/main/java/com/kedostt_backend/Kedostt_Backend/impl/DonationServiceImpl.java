package com.kedostt_backend.Kedostt_Backend.impl;

import com.kedostt_backend.Kedostt_Backend.dto.DonationDto;
import com.kedostt_backend.Kedostt_Backend.mapper.DonationMapper;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.model.Donation;
import com.kedostt_backend.Kedostt_Backend.repository.AnimalRepository;
import com.kedostt_backend.Kedostt_Backend.repository.DonationRepository;
import com.kedostt_backend.Kedostt_Backend.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final AnimalRepository animalRepository;
    private final DonationMapper donationMapper;

    @Override
    public DonationDto createDonation(DonationDto donationDto) {
        Animal animal = animalRepository.findById(donationDto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı!"));

        Donation donation = donationMapper.toEntity(donationDto, animal);
        return donationMapper.toDto(donationRepository.save(donation));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonationDto> getAllDonations(){
        return donationRepository.findAll()
                .stream()
                .map(donationMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public DonationDto getDonationById(Long id) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bağış bulunamadı!"));
        return donationMapper.toDto(donation);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonationDto> getDonationsByAnimalId(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı!"));
        return animal.getDonations()
                .stream()
                .map(donationMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public DonationDto updateDonation(Long id, DonationDto donationDto) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bağış bulunamadı"));

        Animal animal = animalRepository.findById(donationDto.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));

        donation.setAmount(donationDto.getAmount());
        donation.setDonorName(donationDto.getDonorName());
        donation.setMessage(donationDto.getMessage());
        donation.setAnimal(animal);

        return donationMapper.toDto(donationRepository.save(donation));
    }

    @Override
    public DonationDto deleteDonation(Long id) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bağış bulunamadı"));
        donationRepository.delete(donation);
        return donationMapper.toDto(donation);
    }
}
