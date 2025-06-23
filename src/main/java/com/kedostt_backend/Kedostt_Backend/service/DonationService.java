package com.kedostt_backend.Kedostt_Backend.service;

import com.kedostt_backend.Kedostt_Backend.dto.DonationRequest;
import com.kedostt_backend.Kedostt_Backend.model.Donation;

import java.util.List;

public interface DonationService {

    Donation createDonation(DonationRequest request);
    List<Donation> getAllDonations();
    Donation getDonationById(Long Id);
    List<Donation> getDonationsByAnimalId(Long id);
    Donation updateDonation(Long id, DonationRequest request);
    Donation deleteDonation(Long id);
}
