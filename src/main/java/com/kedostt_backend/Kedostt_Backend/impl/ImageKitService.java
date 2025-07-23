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

        Result result = ImageKit.getInstance().upload(fileCreateRequest);

        if (result.getUrl() == null || result.getFileId() == null) {
            throw new RuntimeException("Upload başarısız.");
        }

        Map<String, String> response = new HashMap<>();
        response.put("url", result.getUrl());
        response.put("fileId", result.getFileId());
        return response;
    }

    public String generateImageUrl(String filePath) {
        Map<String, Object> options = new HashMap<>();
        options.put("path", filePath);

        return ImageKit.getInstance().getUrl(options);
    }

    public void deleteImage(String fileId) throws Exception {
        ImageKit.getInstance().deleteFile(fileId);
    }
}
