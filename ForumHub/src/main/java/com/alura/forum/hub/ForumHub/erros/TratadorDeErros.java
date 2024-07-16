package com.alura.forum.hub.ForumHub.erros;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404 () {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400 (MethodArgumentNotValidException e) {

        List<DadosErros> erros = e.getFieldErrors().stream().map(DadosErros::new).toList();
        return ResponseEntity.badRequest().body(erros);
    }

    private record DadosErros(String campos, String erro){
        public DadosErros(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
