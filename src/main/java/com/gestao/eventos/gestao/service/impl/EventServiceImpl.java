package com.gestao.eventos.gestao.service.impl;


import org.springframework.stereotype.Service;

import com.gestao.eventos.gestao.entity.EventEntity;
import com.gestao.eventos.gestao.repository.EventRepository;
import com.gestao.eventos.gestao.service.EventService;

import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Page<EventEntity> listEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataHoraEvento").descending());
        return eventRepository.findAll(pageable);
    }
    
    @Override
    public Optional<EventEntity> getEventById(Long id) {
        Optional<EventEntity> optionalEvent = eventRepository.findById(id);
        return optionalEvent.filter(event -> !event.isDeletado());
    }
    
    @Override
    public EventEntity createEvent(EventEntity event) {
        event.setDeletado(false); // Garante que novos eventos não venham marcados como deletados
        return eventRepository.save(event);
    }
    
    @Override
    public Optional<EventEntity> updateEvent(Long id, EventEntity updatedEvent) {
        Optional<EventEntity> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isEmpty()) {
            return Optional.empty();
        }

        EventEntity existingEvent = optionalEvent.get();

        existingEvent.setTitulo(updatedEvent.getTitulo());
        existingEvent.setDescricao(updatedEvent.getDescricao());
        existingEvent.setDataHoraEvento(updatedEvent.getDataHoraEvento());
        existingEvent.setLocal(updatedEvent.getLocal());

        return Optional.of(eventRepository.save(existingEvent));
    }
    
    @Override
    public void deleteEvent(Long id) {
        Optional<EventEntity> optionalEvent = eventRepository.findById(id);

        if (optionalEvent.isEmpty() || optionalEvent.get().isDeletado()) {
            throw new EntityNotFoundException("Evento não encontrado ou já deletado.");
        }

        EventEntity event = optionalEvent.get();
        event.setDeletado(true);
        eventRepository.save(event);
    }
}

