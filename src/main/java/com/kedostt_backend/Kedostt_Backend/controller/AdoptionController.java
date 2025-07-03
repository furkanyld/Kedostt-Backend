package com.kedostt_backend.Kedostt_Backend.controller;

import com.kedostt_backend.Kedostt_Backend.dto.AdoptionDto;
import com.kedostt_backend.Kedostt_Backend.service.AdoptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoption")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;

    @PostMapping
    public ResponseEntity<AdoptionDto> apply(@RequestBody @Valid AdoptionDto dto, Authentication authentication) {
        AdoptionDto created = adoptionService.createAdoption(dto, authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdoptionDto>> getAllAdoptions() {
        return ResponseEntity.ok(adoptionService.getAllAdoptions());
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<AdoptionDto>> getOwnAdoptions(Authentication authentication){
        String username = authentication.getName();
        List<AdoptionDto> adoptions = adoptionService.getAdoptionsByUsername(username);
        return ResponseEntity.ok(adoptions);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdoptionDto> updateStatus(@PathVariable Long id, @RequestParam String status) {

        AdoptionDto updated = adoptionService.updateAdoptionStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/adoptions/{adoptionId}/accept")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> acceptAdoption(@PathVariable Long adoptionId) {
        adoptionService.acceptAdoption(adoptionId);
        return ResponseEntity.ok("Sahiplenme isteği kabul edildi.");
    }

    @PostMapping("/adoptions/{adoptionId}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> rejectAdoption(@PathVariable Long adoptionId) {
        adoptionService.rejectAdoption(adoptionId);
        return ResponseEntity.ok("Sahiplenme isteği reddedildi.");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAdoption(@PathVariable Long id) {
        adoptionService.deleteAdoption(id);
        return ResponseEntity.noContent().build();
    }

}
