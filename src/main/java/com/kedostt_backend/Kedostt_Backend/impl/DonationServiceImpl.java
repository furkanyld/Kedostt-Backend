package com.kedostt_backend.Kedostt_Backend.impl;

import com.kedostt_backend.Kedostt_Backend.dto.DonationRequest;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.model.Donation;
import com.kedostt_backend.Kedostt_Backend.repository.AnimalRepository;
import com.kedostt_backend.Kedostt_Backend.repository.DonationRepository;
import com.kedostt_backend.Kedostt_Backend.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {
    private final DonationRepository donationRepository;
    private final AnimalRepository animalRepository;

    @Override
    public Donation createDonation(DonationRequest request){
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(()-> new RuntimeException("Hayvan Bulunamadı!"));

        Donation donation = new Donation();
        donation.setAmount(request.getAmount());
        donation.setDonorName(request.getDonorName());
        donation.setMessage(request.getMessage());
        donation.setAnimal(animal);

        return donationRepository.save(donation);
    }

    @Override
    public List<Donation> getAllDonations(){
        return donationRepository.findAll();
    }

    @Override
    public Donation getDonationById(Long id){
        return donationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Bağış Bulunamadı!"));
    }

    @Override
    public List<Donation> getDonationsByAnimalId(Long id){
       Animal animal = animalRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Hayvan bulunamadı!"));
       return animal.getDonations();
    }

    @Override
    public Donation updateDonation(Long id, DonationRequest request) {
        Donation donation = donationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bağış bulunamadı"));

        Animal animal = animalRepository.findById(request.getAnimalId())
            .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));

        donation.setAmount(request.getAmount());
        donation.setDonorName(request.getDonorName());
        donation.setMessage(request.getMessage());
        donation.setAnimal(animal);

        return donationRepository.save(donation);
    }

    @Override
    public Donation deleteDonation(Long id) {
        Donation donation = donationRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Bağış bulunamadı"));

        donationRepository.delete(donation);
        return donation;
    }
}
