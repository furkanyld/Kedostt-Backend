package com.kedostt_backend.Kedostt_Backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    // Dosyaların kaydedileceği klasör - kendi sistemine göre ayarla (mutlaka var olmalı)
    private final String uploadDir = System.getProperty("user.dir") + "/uploads";

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // uploads klasörü yoksa oluştur
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // UUID ile benzersiz dosya adı oluştur
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

            // Dosyayı kaydet
            Path filePath = uploadPath.resolve(filename);
            Files.write(filePath, file.getBytes());

            // Frontend'e dönecek URL (Statik dosya erişimi için uygun URL)
            String fileUrl = "/uploads/" + filename;

            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("error", "Dosya yüklenirken hata oluştu");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
