package com.kedostt_backend.Kedostt_Backend.controller;

import com.kedostt_backend.Kedostt_Backend.dto.DonationDto;
import com.kedostt_backend.Kedostt_Backend.service.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @PostMapping
    public ResponseEntity<DonationDto> donate(@RequestBody @Valid DonationDto request) {
        DonationDto created = donationService.createDonation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DonationDto> updateDonation(@PathVariable Long id,
                                                      @RequestBody @Valid DonationDto request) {
        return ResponseEntity.ok(donationService.updateDonation(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DonationDto> deleteDonation(@PathVariable Long id) {
        return ResponseEntity.ok(donationService.deleteDonation(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DonationDto>> getAllDonations() {
        return ResponseEntity.ok(donationService.getAllDonations());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DonationDto> getDonationById(@PathVariable Long id) {
        return ResponseEntity.ok(donationService.getDonationById(id));
    }

    @GetMapping("/animal/{animalId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DonationDto>> getDonationsByAnimalId(@PathVariable Long animalId) {
        return ResponseEntity.ok(donationService.getDonationsByAnimalId(animalId));
    }
}
