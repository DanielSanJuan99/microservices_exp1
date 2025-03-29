package com.project.sumativa.exception;

// MANEJO DE ERRORES EN EL CONTROLADOR DE USUARIOS
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFound extends RuntimeException {
    
    public UsuarioNotFound(Long id) {
        super("El usuario con id " + id + " no fue encontrado.");
    }
    
}
