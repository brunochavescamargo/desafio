package com.gestao.eventos.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestao.eventos.gestao.entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity, Long> { 

}