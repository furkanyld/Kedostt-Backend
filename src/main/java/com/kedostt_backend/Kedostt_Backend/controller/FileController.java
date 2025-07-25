package com.kedostt_backend.Kedostt_Backend.controller;

import com.kedostt_backend.Kedostt_Backend.impl.ImageKitService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final ImageKitService imageKitService;

    @GetMapping("/uploads/{fileName:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) throws IOException {
        Path filePath = Paths.get("uploads").resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(filePath);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .body(resource);
    }

    @PostMapping("/images/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, String> imageInfo = imageKitService.uploadImage(file);
            return ResponseEntity.ok(imageInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Upload failed: " + e.getMessage()));
        }
    }


    @DeleteMapping("/images/{fileId}")
    public ResponseEntity<String> deleteImage(@PathVariable String fileId) {
        try {
            imageKitService.deleteImage(fileId);
            return ResponseEntity.ok("Silindi");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Silinemedi: " + e.getMessage());
        }
    }
}
