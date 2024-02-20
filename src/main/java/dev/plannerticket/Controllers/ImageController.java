package dev.plannerticket.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.plannerticket.Services.FileStorageService;


@RestController
@RequestMapping(path = "${api-endpoint}")

public class ImageController {
        @Autowired
        private FileStorageService fileStorageService;

    @PostMapping(path = "/images")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        // Utiliza FileStorageService para guardar la imagen
        String fileName = fileStorageService.storeFile(file);
        // Respuesta con el nombre del archivo
        return ResponseEntity.status(HttpStatus.CREATED).body("Archivo subido con éxito: " + fileName);
    }

    // *** SIN @ EXCEPCIÓN ********
/*     @GetMapping("/images/{fileName:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        try {
            Resource file = fileStorageService.loadAsResource(fileName);
            // Verifica si el archivo existe
            if (!file.exists()) {
                throw new FileNotFoundException("Archivo no encontrado " + fileName);
            }
            // Establece el tipo de contenido basado en el tipo de archivo
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/jpeg"))
                    .body(file);
        } catch (FileNotFoundException e) {
            // Manejar la excepción FileNotFoundException
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    } */



    // *** CON  @ EXCEPCIÓN ********
    @GetMapping("/images/{fileName:.+}")
public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
    Resource file = fileStorageService.loadAsResource(fileName);
    return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("image/jpeg"))
            .body(file);
}
    
}
