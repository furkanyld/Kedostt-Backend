package com.kedostt_backend.Kedostt_Backend.controller;

import com.kedostt_backend.Kedostt_Backend.dto.AnimalRequest;
import com.kedostt_backend.Kedostt_Backend.dto.AnimalResponse;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.repository.AnimalRepository;
import com.kedostt_backend.Kedostt_Backend.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id){
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody AnimalRequest request) {
        return ResponseEntity.ok(animalService.createAnimal(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest request) {
        AnimalResponse updated = animalService.updateAnimal(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.ok("Hayvan silindi: "+id);
    }

    @PutMapping("/{id}/toggle-visibility")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> toggleVisibility(@PathVariable Long id) {
        animalService.toggleVisibility(id);
        return ResponseEntity.ok("Yayın durumu değiştirildi.");
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AnimalResponse> createAnimalWithFiles(
            @RequestParam("name") String name,
            @RequestParam("species") String species,
            @RequestParam("breed") String breed,
            @RequestParam("ageYears") int ageYears,
            @RequestParam("ageMonths") int ageMonths,
            @RequestParam("gender") String gender,
            @RequestParam("description") String description,
            @RequestParam("images") List<MultipartFile> images,
            @RequestParam(value = "video", required = false) MultipartFile video,
            @RequestParam(value = "visible", defaultValue = "true") boolean visible
    ) throws IOException {
        return ResponseEntity.ok(animalService.createAnimalWithFiles(
                name, species, breed, ageYears, ageMonths, gender, description, images, video, visible
        ));
    }

    @PutMapping("/upload/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AnimalResponse> updateAnimalWithFiles(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("species") String species,
            @RequestParam("breed") String breed,
            @RequestParam("ageYears") int ageYears,
            @RequestParam("ageMonths") int ageMonths,
            @RequestParam("gender") String gender,
            @RequestParam("description") String description,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestParam(value = "video", required = false) MultipartFile video,
            @RequestParam(value = "visible", defaultValue = "true") boolean visible
    ) throws IOException {
        return ResponseEntity.ok(animalService.updateAnimalWithFiles(
                id, name, species, breed, ageYears, ageMonths, gender, description, images, video, visible
        ));
    }
}
