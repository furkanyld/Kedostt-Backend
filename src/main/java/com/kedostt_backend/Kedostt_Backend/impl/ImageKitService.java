package com.kedostt_backend.Kedostt_Backend.impl;

import io.imagekit.sdk.ImageKit;
import io.imagekit.sdk.config.Configuration;
import io.imagekit.sdk.models.FileCreateRequest;
import io.imagekit.sdk.models.results.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageKitService {

    public ImageKitService(
            @Value("${imagekit.publicKey}") String publicKey,
            @Value("${imagekit.privateKey}") String privateKey,
            @Value("${imagekit.urlEndpoint}") String urlEndpoint
    ) {
        Configuration config = new Configuration(publicKey, privateKey, urlEndpoint);
        ImageKit.getInstance().setConfig(config);
    }

    public Map<String, String> uploadImage(MultipartFile file) throws Exception {
        FileCreateRequest fileCreateRequest = new FileCreateRequest(file.getBytes(), file.getOriginalFilename());
        fileCreateRequest.setTags(List.of("Kedostt"));
        fileCreateRequest.setResponseFields(List.of("thumbnail", "tags"));
        fileCreateRequest.setUseUniqueFileName(false);

        Result result = ImageKit.getInstance().upload(fileCreateRequest);

        if (result.getUrl() == null || result.getFileId() == null) {
            throw new RuntimeException("Upload başarısız.");
        }

        Map<String, String> map = new HashMap<>();
        map.put("url", result.getUrl());
        map.put("fileId", result.getFileId());

        return map;
    }

    public Map<String, String> uploadVideo(MultipartFile file) throws Exception {
        String mimeType = file.getContentType();
        if (mimeType == null || !mimeType.startsWith("video/")) {
            throw new IllegalArgumentException("Geçersiz video formatı.");
        }

        FileCreateRequest fileCreateRequest = new FileCreateRequest(file.getBytes(), file.getOriginalFilename());
        fileCreateRequest.setTags(List.of("Kedostt", "video"));
        fileCreateRequest.setUseUniqueFileName(true);
        fileCreateRequest.setResponseFields(List.of("tags")); // thumbnail işe yaramaz, çünkü video

        Result result = ImageKit.getInstance().upload(fileCreateRequest);

        if (result.getUrl() == null || result.getFileId() == null) {
            throw new RuntimeException("Video upload başarısız.");
        }

        Map<String, String> map = new HashMap<>();
        map.put("url", result.getUrl());
        map.put("fileId", result.getFileId());

        return map;
    }

    public void deleteImage(String fileId) throws Exception {
        ImageKit.getInstance().deleteFile(fileId);
    }
}
