package com.alura.forum.hub.ForumHub.repository;

import com.alura.forum.hub.ForumHub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicosRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findByAtivoTrue(Pageable paginacao);
}