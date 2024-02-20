package dev.plannerticket.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.plannerticket.Models.Event;
import dev.plannerticket.Services.EventService;
import io.micrometer.common.lang.NonNull;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("${api-endpoint}/events")
public class EventController {

    @Autowired
    private EventService eventService;   
  
    /* @Autowired
    private FileStorageService fileStorageService; */
    
    @GetMapping(path = "")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Event> show(@NonNull @PathVariable("id") Long id) throws Exception {
        Event event = eventService.getEventbyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }
/* 
    @PostMapping(path = "")
    public ResponseEntity<Event> store(@RequestBody Event newevent, @RequestParam("file") MultipartFile file) throws Exception {
        // Guardar la imagen y obtener el nombre del archivo
        String imageName = fileStorageService.storeFile(file);
        // Actualizar el evento con el nombre de la imagen
        newevent.setImage("/images/" + imageName);
        // Guardar el evento en la base de datos
        eventService.saveEvent(newevent);
        // Devolver el evento creado
        return ResponseEntity.status(HttpStatus.CREATED).body(newevent);
    }
 */
    @PostMapping(path = "")
    public ResponseEntity<Event> store(@RequestBody Event newevent) throws Exception {
        // El objeto Event tiene que incluir el nombre de la imagen
        // Guardar el evento en la base de datos        
        eventService.saveEvent(newevent);
        // Devolver el evento creado
        return ResponseEntity.status(HttpStatus.CREATED).body(newevent);
    }

    

/* 
    @DeleteMapping("/{id}")
    public void deleteEvent(Long id) {
        eventService.deleteEvent(id);
    }
 */
}

