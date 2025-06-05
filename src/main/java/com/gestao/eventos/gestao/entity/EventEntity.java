package com.gestao.eventos.gestao.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Where(clause = "deletado <> true")
@Table(name = "EVENT")
public class EventEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotBlank
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
    private String titulo;
    
    @NotBlank
    @Column(length = 1000)
    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres.")
    private String descricao;
    
    @Column(name = "data_hora_evento")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @FutureOrPresent(message = "A data e hora do evento não pode estar no passado.")
    private LocalDateTime dataHoraEvento;
    
    @NotBlank
    @Column(length = 200)
    @Size(max = 200, message = "O local deve ter no máximo 200 caracteres.")
    private String local;
    
    @Column(nullable = false)
    private Boolean deletado = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataHoraEvento() {
		return dataHoraEvento;
	}

	public void setDataHoraEvento(LocalDateTime dataHoraEvento) {
		this.dataHoraEvento = dataHoraEvento;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Boolean  isDeletado() {
		return deletado;
	}

	public void setDeletado(Boolean  deletado) {
		this.deletado = deletado;
	}
	    
}
