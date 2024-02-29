package dev.plannerticket.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import dev.plannerticket.Models.Event;
import dev.plannerticket.Services.EventService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("${api-endpoint}/events")
public class EventController {

    @Autowired
    private EventService eventService;   
  
    @GetMapping(path = "")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Event> show(@PathVariable("id") Long id) throws Exception {
        Event event = eventService.getEventbyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

   @PostMapping(path = "")
    public ResponseEntity<Event> store(@RequestBody Event newevent) throws Exception {      
        eventService.saveEvent(newevent);      
        return ResponseEntity.status(HttpStatus.CREATED).body(newevent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
       Event updatedEvent = eventService.updateEvent(id, event);
       return ResponseEntity.ok(updatedEvent);
    }
   
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Exception {
        eventService.deleteEvent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
