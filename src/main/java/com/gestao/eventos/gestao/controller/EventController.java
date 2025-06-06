package com.gestao.eventos.gestao.controller;


import java.util.Optional;

import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestao.eventos.gestao.dto.EventDTO;
import com.gestao.eventos.gestao.entity.EventEntity;
import com.gestao.eventos.gestao.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    
    @Operation(summary = "Listar todos os eventos com paginação (page, size)")
    @GetMapping
    public Page<EventEntity> listEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return eventService.listEvents(page, size);
    }
    
    @Operation(summary = "Detalhes do evento")
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(event -> ResponseEntity.ok(new EventDTO(event)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria novo evento")
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody @Valid EventEntity event) {
        EventEntity saved = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EventDTO(saved));
    }
      
    @Operation(summary = "Atualiza evento existente")
    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable Long id, @RequestBody @Valid EventEntity updatedEvent) {
        Optional<EventEntity> optionalUpdated = eventService.updateEvent(id, updatedEvent);

        return optionalUpdated
                .map(event -> ResponseEntity.ok(new EventDTO(event)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Remove (soft delete)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}

