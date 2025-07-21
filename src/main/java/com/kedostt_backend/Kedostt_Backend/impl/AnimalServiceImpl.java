package com.kedostt_backend.Kedostt_Backend.impl;

import com.kedostt_backend.Kedostt_Backend.dto.AnimalRequest;
import com.kedostt_backend.Kedostt_Backend.dto.AnimalResponse;
import com.kedostt_backend.Kedostt_Backend.mapper.AnimalMapper;
import com.kedostt_backend.Kedostt_Backend.model.Animal;
import com.kedostt_backend.Kedostt_Backend.repository.AnimalRepository;
import com.kedostt_backend.Kedostt_Backend.service.AnimalService;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    private final AnimalMapper animalMapper;

    @Override
    public AnimalResponse createAnimal(AnimalRequest request){
        Animal animal = animalMapper.toEntity(request);
        Animal saved = animalRepository.save(animal);
        return animalMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnimalResponse> getAllAnimals(){
        return animalRepository.findAll().stream()
                .map(animalMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AnimalResponse getAnimalById(Long id){
        Animal animal = animalRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Hayvan Bulunamadı: "+id));
        return animalMapper.toDto(animal);
    }

    @Override
    @Transactional
    public AnimalResponse updateAnimal(Long id, AnimalRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı!"));

        animal.setName(request.getName());
        animal.setDescription(request.getDescription());
        animal.setImageUrls(request.getImageUrls());
        animal.setVideoUrl(request.getVideoUrl());

        Animal saved = animalRepository.save(animal);
        return animalMapper.toDto(saved);
    }

    @Override
    public void toggleVisibility(Long animalId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));
        animal.setVisible(!animal.isVisible());
        animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Silinecek hayvan bulunamadı: " + id);
        }
        animalRepository.deleteById(id);
    }

    @Override
    public AnimalResponse createAnimalWithFiles(
            String name,
            String species,
            String breed,
            int ageYears,
            int ageMonths,
            String gender,
            String description,
            List<MultipartFile> images,
            MultipartFile video,
            boolean visible
    ) throws IOException {

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile image : images) {
            String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path imagePath = Paths.get("uploads", imageName);
            Files.write(imagePath, image.getBytes());
            imageUrls.add("/uploads/" + imageName);
        }

        String videoUrl = null;
        if (video != null && !video.isEmpty()) {
            String videoName = UUID.randomUUID() + "_" + video.getOriginalFilename();
            Path videoPath = Paths.get("uploads", videoName);
            Files.write(videoPath, video.getBytes());
            videoUrl = "/uploads/" + videoName;
        }

        AnimalRequest request = AnimalRequest.builder()
                .name(name)
                .species(species)
                .breed(breed)
                .ageYears(ageYears)
                .ageMonths(ageMonths)
                .gender(gender)
                .description(description)
                .imageUrls(imageUrls)
                .videoUrl(videoUrl)
                .isVisible(visible)
                .build();

        Animal animal = animalMapper.toEntity(request);
        Animal saved = animalRepository.save(animal);
        return animalMapper.toDto(saved);
    }

    @Override
    @Transactional
    public AnimalResponse updateAnimalWithFiles(
            Long id,
            String name,
            String species,
            String breed,
            int ageYears,
            int ageMonths,
            String gender,
            String description,
            List<MultipartFile> images,
            MultipartFile video,
            boolean visible
    ) throws IOException {

        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı: " + id));

        // 📦 uploads klasörü oluştur
        String uploadDir = "uploads/";
        Files.createDirectories(Paths.get(uploadDir));

        // 🔁 Eski görselleri sil ve veritabanından kaldır
        if (animal.getImageUrls() != null) {
            for (String oldPath : animal.getImageUrls()) {
                Files.deleteIfExists(Paths.get(oldPath));
            }
        }

        // 📷 Yeni görselleri kaydet
        List<String> newImageUrls = new ArrayList<>();
        if (images != null) {
            for (MultipartFile file : images) {
                if (!file.isEmpty()) {
                    String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path filePath = Paths.get(uploadDir + fileName);
                    Files.write(filePath, file.getBytes());
                    newImageUrls.add(filePath.toString());
                }
            }
        }

        // 🎥 Videoyu kaydet (isteğe bağlı)
        String videoPath = null;
        if (video != null && !video.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + video.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.write(filePath, video.getBytes());
            videoPath = filePath.toString();
        }

        // 📝 Hayvan verisini güncelle
        animal.setName(name);
        animal.setSpecies(species);
        animal.setBreed(breed);
        animal.setAgeYears(ageYears);
        animal.setAgeMonths(ageMonths);
        animal.setGender(gender);
        animal.setDescription(description);
        animal.setImageUrls(newImageUrls);
        animal.setVideoUrl(videoPath);
        animal.setVisible(visible);

        Animal saved = animalRepository.save(animal);
        return animalMapper.toDto(saved);
    }
}
