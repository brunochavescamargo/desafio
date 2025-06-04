package com.gestao.eventos.gestao.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.gestao.eventos.gestao.entity.EventEntity;

public interface EventService {
	Page<EventEntity> listEvents(int page, int size);
	Optional<EventEntity> getEventById(Long id);
    EventEntity createEvent(EventEntity event);  
    Optional<EventEntity> updateEvent(Long id, EventEntity updatedEvent); 
    void deleteEvent(Long id);
}
