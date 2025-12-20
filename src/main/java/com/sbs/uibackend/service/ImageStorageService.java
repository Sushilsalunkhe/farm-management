package com.sbs.uibackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageStorageService {

    private final Path uploadDir =
            Paths.get(System.getProperty("user.dir"), "uploads", "products");

    public String saveImage(MultipartFile file) throws IOException {

        // ✅ Ensure directory exists
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        String filename =
                System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path filePath = uploadDir.resolve(filename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // return relative URL
        return "/uploads/products/" + filename;
    }
}

