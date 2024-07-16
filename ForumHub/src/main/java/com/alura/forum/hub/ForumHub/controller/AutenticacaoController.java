package com.alura.forum.hub.ForumHub.controller;

import com.alura.forum.hub.ForumHub.dto.AutentificacaoUsuarioDto;
import com.alura.forum.hub.ForumHub.dto.JWTtokenDto;
import com.alura.forum.hub.ForumHub.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity autenticarUsuario (@RequestBody @Valid AutentificacaoUsuarioDto autentificacaoUsuarioDto) {

        Authentication tokenAuth = new UsernamePasswordAuthenticationToken(autentificacaoUsuarioDto.nomeUsuario(),
                autentificacaoUsuarioDto.chave());
        Authentication usuarioAutenticado = authenticationManager.authenticate(tokenAuth);
        var jwtToken = tokenService.gerarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTtokenDto(jwtToken));
    }
}