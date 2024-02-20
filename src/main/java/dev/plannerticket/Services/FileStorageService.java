package dev.plannerticket.Services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import dev.plannerticket.Exceptions.FileException;

@Service
public class FileStorageService {
    private final String uploadDir = "src/main/resources/static/images/";

    public String storeFile(MultipartFile file) {
        // Genera un nombre de archivo único para evitar conflictos
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(uploadDir, fileName);
    
        // Copia el archivo al directorio de destino
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {            
            throw new RuntimeException("No se ha podido almacenar el archivo " + fileName + ". Intentarlo de nuevo.");
        }
        return fileName;
    }
    
    public Resource loadAsResource(String fileName) {
        try {
            Path filePath = Paths.get(uploadDir, fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileException("Archivo no encontrado " + fileName, HttpStatus.NOT_FOUND);

            }
            } catch (MalformedURLException ex) {
                throw new FileException("Archivo no encontrado " + fileName, HttpStatus.NOT_FOUND);
        }
    }
}
