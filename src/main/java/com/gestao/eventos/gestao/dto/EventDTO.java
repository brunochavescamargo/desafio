package com.gestao.eventos.gestao.dto;


import java.time.LocalDateTime;

import com.gestao.eventos.gestao.entity.EventEntity;

public class EventDTO {
	private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHoraEvento;
    private String local;
    private Boolean deletado;

    public EventDTO(EventEntity event) {
        this.id = event.getId();
        this.titulo = event.getTitulo();
        this.descricao = event.getDescricao();
        this.dataHoraEvento = event.getDataHoraEvento();
        this.local = event.getLocal();
        this.deletado = event.isDeletado();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHoraEvento() {
        return dataHoraEvento;
    }

    public String getLocal() {
        return local;
    }

	public Boolean getDeletado() {
		return deletado;
	}

	public void setDeletado(Boolean deletado) {
		this.deletado = deletado;
	}
    
}

