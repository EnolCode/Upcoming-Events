package com.Upcoming.Events.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.services.EventServiceImpl;

@RestController
@RequestMapping("/api/events")
public class EventController {

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @Autowired
    private EventServiceImpl eventService;

    // Crear nuevo usuario
    @Transactional
    @PostMapping(value = "add", consumes = "application/*")
    public ResponseEntity<?> create(@RequestBody Event event) {

        System.out.println("----------------------------");
        System.out.println(event.getTitle());
        System.out.println(event.getStyle());
        System.out.println("----------------------------");

        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event));
    }

    // Leer un usuario
    @Transactional
    @GetMapping
    public List<Event> getAll() {
        return eventService.findAll();
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional<Event> oEvent = eventService.findById(id);
        if (!oEvent.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(oEvent);

    }

    // Actualizar usuario
    @Transactional
    @PutMapping("/updated/{id}")
    public ResponseEntity<?> update(@RequestBody Event eventDetails, @PathVariable Long id) {
        Optional<Event> event = eventService.findById(id);
        if (!event.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        event.get().setTitle(eventDetails.getTitle());
        event.get().setDate_hour(eventDetails.getDate_hour());
        event.get().setMax_participants(eventDetails.getMax_participants());
        event.get().setDescription(eventDetails.getDescription());
        event.get().setStyle(eventDetails.getStyle());
        event.get().setActual_participants(eventDetails.getActual_participants());

        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.save(event.get()));

    }

    // Eliminar usuario
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!eventService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        eventService.deleteById(id);
        return ResponseEntity.ok("Show deleted successfully");

    }
}
