package com.kedostt_backend.Kedostt_Backend.controller;

import com.kedostt_backend.Kedostt_Backend.dto.DonationRequest;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.model.Donation;
import com.kedostt_backend.Kedostt_Backend.service.AnimalService;
import com.kedostt_backend.Kedostt_Backend.service.DonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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
    public ResponseEntity<Donation> donate(@RequestBody @Valid DonationRequest request){
        Donation created = donationService.createDonation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Donation> updateDonation(@PathVariable Long id,
                                                   @RequestBody @Valid DonationRequest request) {
        return ResponseEntity.ok(donationService.updateDonation(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Donation> deleteDonation(@PathVariable Long id) {
        return ResponseEntity.ok(donationService.deleteDonation(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Donation>> getAllDonations(){
        List<Donation> donations = donationService.getAllDonations();
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Donation> getDonationById(@PathVariable Long id){
         Donation donation = donationService.getDonationById(id);
         return ResponseEntity.ok(donation);
    }

    @GetMapping("/animal/{animalId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Donation>> getDonationsByAnimalId(@PathVariable Long animalId){
        List<Donation> donations = donationService.getDonationsByAnimalId(animalId);
        return ResponseEntity.ok(donations);
    }
}
