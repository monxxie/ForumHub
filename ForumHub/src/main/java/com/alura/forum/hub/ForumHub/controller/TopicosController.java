package com.alura.forum.hub.ForumHub.controller;

import com.alura.forum.hub.ForumHub.dto.AtualizarTopicosDto;
import com.alura.forum.hub.ForumHub.dto.ListaTopicosDto;
import com.alura.forum.hub.ForumHub.dto.TopicosDto;
import com.alura.forum.hub.ForumHub.dto.TopicosPorIdDto;
import com.alura.forum.hub.ForumHub.model.Topico;
import com.alura.forum.hub.ForumHub.repository.TopicosRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topicos")
public class TopicosController {

    private TopicosRepository topicosRepository;

    public TopicosController(TopicosRepository topicosRepository) {
        this.topicosRepository = topicosRepository;
    }

    @PostMapping
    public ResponseEntity<TopicosPorIdDto> criarTopico(@RequestBody TopicosDto topicosDto,
                                                       UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicosRepository.save(new Topico(topicosDto));
        TopicosPorIdDto topicosPorId = new TopicosPorIdDto(topico.getId(),topico.getTitulo(),
                topico.getMensagem(),topico.getAutor(),topico.getCurso());
        URI url = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(topicosPorId);
    }

    @GetMapping
    public ResponseEntity<Page<ListaTopicosDto>> mostrarTopicos (@PageableDefault(size = 10) Pageable paginacao) {
        return ResponseEntity.ok(topicosRepository.findByAtivoTrue(paginacao).map(ListaTopicosDto::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<TopicosPorIdDto> atualizarTopicos(@RequestBody AtualizarTopicosDto atualizarTopicosDto) {

        Topico topico = topicosRepository.getReferenceById(atualizarTopicosDto.id());
        topico.atualizarTopico(atualizarTopicosDto);
        return ResponseEntity.ok(new TopicosPorIdDto(topico.getId(),topico.getTitulo(),
                topico.getMensagem(),topico.getAutor(),topico.getCurso()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicosPorIdDto> buscarPorId (@PathVariable Long id) {

        Topico topico = topicosRepository.getReferenceById(id);
        TopicosPorIdDto topicosPorId = new TopicosPorIdDto(topico.getId(),topico.getTitulo(),
                topico.getMensagem(),topico.getAutor(),topico.getCurso());
        return ResponseEntity.ok(topicosPorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity desativar(@PathVariable Long id) {

        Topico topico = topicosRepository.getReferenceById(id);
        topicosRepository.delete(topico);
        return ResponseEntity.ok().build();
    }
}