package dev.plannerticket.Services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import dev.plannerticket.Exceptions.FileException;
import dev.plannerticket.Models.Event;
import dev.plannerticket.Repositories.EventRepository;

@Service
public class EventService {

    private EventRepository eventRepository;    

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    public Event getEventbyId(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
   
    public Event saveEvent(Event event)  {
        return eventRepository.save(event);
    }  
    
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new FileException("Evento no encontrado", HttpStatus.NOT_FOUND));
        
        // Actualiza los campos del evento existente con los valores del evento actualizado
        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setCapacity(updatedEvent.getCapacity());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setTime(updatedEvent.getTime());
        existingEvent.setImage(updatedEvent.getImage());

        return eventRepository.save(existingEvent);
    }
}
