package com.eduegy.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class StorageService {

    private final Path baseDir;

    public StorageService(@Value("${eduegy.storage.base-dir:uploads}") String baseDir) {
        this.baseDir = Paths.get(baseDir).toAbsolutePath().normalize();
    }

    public Path ensureDir(String... parts) throws IOException {
        Path dir = baseDir;
        for (String p : parts) dir = dir.resolve(p);
        Files.createDirectories(dir);
        return dir;
    }

    public String store(MultipartFile file, Path targetDir, String storedName) throws IOException {
        if (file.isEmpty()) throw new IOException("File is empty");
        String name = storedName != null ? storedName : sanitize(file.getOriginalFilename());
        if (name == null || name.isBlank()) name = "file";
        Path dest = targetDir.resolve(name).normalize();
        if (!dest.startsWith(targetDir)) throw new IOException("Invalid path");
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);
        return dest.getFileName().toString();
    }

    public static String sanitize(String name) {
        if (name == null) return null;
        // remove path separators
        name = name.replaceAll("[\\/]+", "_");
        // keep it simple
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
