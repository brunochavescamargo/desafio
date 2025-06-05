package com.gestao.eventos.gestao.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.gestao.eventos.gestao.entity.EventEntity;
import com.gestao.eventos.gestao.repository.EventRepository;


class EventServiceImplTest {

	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventServiceImpl eventService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldReturnPaginatedEvents() {
		EventEntity event = new EventEntity();
		event.setId(1L);
		event.setTitulo("Evento Teste");

		Page<EventEntity> page = new PageImpl<>(List.of(event));
		when(eventRepository.findAll(any(Pageable.class))).thenReturn(page);

		Page<EventEntity> result = eventService.listEvents(0, 10);

		assertEquals(1, result.getTotalElements());
		assertEquals("Evento Teste", result.getContent().get(0).getTitulo());
	}

	@Test
	void shouldReturnEventDTOWhenEventExists() {
		EventEntity event = new EventEntity();
		event.setId(1L);
		event.setTitulo("Teste");
		event.setDeletado(false);

		when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

		Optional<EventEntity> result = eventService.getEventById(1L);

		assertTrue(result.isPresent());
		assertEquals("Teste", result.get().getTitulo());
	}

	@Test
	void shouldCreateNewEvent() {
		EventEntity event = new EventEntity();
		event.setTitulo("Novo Evento");

		EventEntity savedEvent = new EventEntity();
		savedEvent.setId(1L);
		savedEvent.setTitulo("Novo Evento");
		savedEvent.setDeletado(false);

		when(eventRepository.save(any(EventEntity.class))).thenReturn(savedEvent);

		EventEntity result = eventService.createEvent(event);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Novo Evento", result.getTitulo());
	}

}
