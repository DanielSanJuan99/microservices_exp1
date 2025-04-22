package com.project.sumativa.controller;

import com.project.sumativa.model.Usuario;
import com.project.sumativa.service.UsuarioService;
import com.project.sumativa.model.ResponseWrapper;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Recupera todos los usuarios
    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        log.debug("GET /usuarios - Recuperando todos los usuarios");
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        if (usuarios.isEmpty()) {
            log.warn("No hay usuarios registrados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados.");
        }

        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "OK",
                usuarios.size(),
                usuarios
            ));
    }

    // Recupera un usuario por ID
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id) {
        log.debug("GET /usuarios/{} - Recuperando usuario por ID", id);
        return usuarioService.getUsuarioById(id);
    }

    // Crea un nuevo usuario
    @PostMapping
    public ResponseEntity<ResponseWrapper<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        log.debug("POST /usuarios - Creando nuevo usuario: {}", usuario);
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(
                    "Usuario creado",
                    1,
                    List.of(createdUsuario)
                ));
    }

    // Actualiza un usuario existente
    @PostMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Usuario>> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        log.debug("POST /usuarios/{} - Actualizando usuario: {}", id);
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "Usuario actualizado",
                1,
                List.of(updatedUsuario)
            ));
    }

    // Elimina un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        log.debug("DELETE /usuarios/{} - Eliminando usuario", id);
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "Usuario eliminado",
                1,
                null
            ));
    }
}
