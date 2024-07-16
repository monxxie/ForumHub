package com.alura.forum.hub.ForumHub.repository;

import com.alura.forum.hub.ForumHub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByNomeDeUsuario(String username);
}