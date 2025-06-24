package com.kedostt_backend.Kedostt_Backend.controller;

import com.kedostt_backend.Kedostt_Backend.dto.AdoptionDto;
import com.kedostt_backend.Kedostt_Backend.service.AdoptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoptions")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionService adoptionService;

    @PostMapping
    public ResponseEntity<AdoptionDto> apply(@RequestBody @Valid AdoptionDto dto) {
        AdoptionDto created = adoptionService.createAdoption(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AdoptionDto>> getAllAdoptions() {
        return ResponseEntity.ok(adoptionService.getAllAdoptions());
    }
}
