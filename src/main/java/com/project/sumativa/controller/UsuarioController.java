package com.project.sumativa.controller;

import com.project.sumativa.model.Usuario;
import com.project.sumativa.service.UsuarioService;
import com.project.sumativa.model.ResponseWrapper;
// import com.project.sumativa.exception.UsuarioNotFound;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados.");
        }

        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "OK",
                usuarios.size(),
                usuarios
            ));
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }
}
