package com.alura.forum.hub.ForumHub.model;

import com.alura.forum.hub.ForumHub.dto.AtualizarTopicosDto;
import com.alura.forum.hub.ForumHub.dto.TopicosDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private String mensagem;
    private String autor;
    private String curso;
    private boolean ativo;

    public Topico(TopicosDto topicosDto)  {
        this.titulo = topicosDto.titulo();
        this.mensagem = topicosDto.mensagem();
        this.autor = topicosDto.autor();
        this.curso = topicosDto.curso();
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void atualizarTopico(AtualizarTopicosDto atualizarTopicosDto) {

        if (atualizarTopicosDto.titulo() != null) {
            this.titulo = atualizarTopicosDto.titulo();
        }
        if (atualizarTopicosDto.mensagem() != null) {
            this.mensagem = atualizarTopicosDto.mensagem();
        }
        if (atualizarTopicosDto.autor() != null) {
            this.autor = atualizarTopicosDto.autor();
        }
        if (atualizarTopicosDto.curso() != null) {
            this.curso = atualizarTopicosDto.curso();
        }


    }
}
