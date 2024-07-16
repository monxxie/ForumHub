package com.alura.forum.hub.ForumHub.dto;

import com.alura.forum.hub.ForumHub.model.Topico;

public record ListaTopicosDto(Long id, String titulo, String mensagem, String autor, String curso) {

    public ListaTopicosDto(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor(), topico.getCurso());
    }
}