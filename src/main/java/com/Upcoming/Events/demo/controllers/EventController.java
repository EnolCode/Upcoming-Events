package com.upcoming.events.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD

import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.services.EventServiceImpl;
=======
import com.upcoming.events.demo.models.Event;
import com.upcoming.events.demo.services.EventServiceImpl;
>>>>>>> origin/develop

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/events")
public class EventController {


    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }
    
    @Autowired
    private EventServiceImpl eventService;
    
//Crear nuevo usuario
@PostMapping(value ="", consumes = "application/*") 
    public ResponseEntity<?> create(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event));
}
//Leer un usuario
@GetMapping
public List<Event> getAll() {
    return eventService.findAll();
}


@GetMapping("/{id}") 
public ResponseEntity<?> read(@PathVariable Long id){
    Optional<Event> oEvent = eventService.findById(id);
    if(!oEvent.isPresent()){
        return ResponseEntity.notFound().build(); 
    }
    return ResponseEntity.ok(oEvent);  

}

//Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Event eventDetails, @PathVariable Long id)  {
        Optional<Event> event = eventService.findById(id);
        if (!event.isPresent()) {
            return ResponseEntity.notFound().build();
    }

    event.get().setTitle(eventDetails.getTitle());
    event.get().setDate_hour(eventDetails.getDate_hour());
    event.get().setMax_participants(eventDetails.getMax_participants());
    event.get().setDescription(eventDetails.getDescription());
    // event.get().setId_image(eventDetails.getId_image());
    event.get().setActual_participants(eventDetails.getActual_participants());

    return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event.get()));

}

//Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id){
        if(!eventService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        eventService.deleteById(id);
        return ResponseEntity.ok().build();

}
}
