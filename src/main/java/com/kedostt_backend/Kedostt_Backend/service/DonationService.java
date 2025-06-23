package com.kedostt_backend.Kedostt_Backend.service;

import com.kedostt_backend.Kedostt_Backend.dto.DonationDto;
import com.kedostt_backend.Kedostt_Backend.model.Donation;

import java.util.List;

public interface DonationService {

    DonationDto createDonation(DonationDto request);
    List<DonationDto> getAllDonations();
    DonationDto getDonationById(Long Id);
    List<DonationDto> getDonationsByAnimalId(Long id);
    DonationDto updateDonation(Long id, DonationDto request);
    DonationDto deleteDonation(Long id);
}
