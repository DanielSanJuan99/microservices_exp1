package com.project.sumativa.controller;

import com.project.sumativa.model.Usuario;
import com.project.sumativa.service.UsuarioService;
import com.project.sumativa.hateoas.UsuarioModelAssembler;
import com.project.sumativa.model.ResponseWrapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Slf4j
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    private final UsuarioModelAssembler assembler;

    public UsuarioController(UsuarioService usuarioService, UsuarioModelAssembler assembler) {
        this.usuarioService = usuarioService;
        this.assembler = assembler;
    }

    // Recupera todos los usuarios
    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        log.info("GET /usuarios - Recuperando todos los usuarios");

        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        if (usuarios.isEmpty()) {
            log.warn("No hay usuarios registrados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados.");
        }

        List<EntityModel<Usuario>> modelos = usuarios.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(modelos,
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel()));
    }

    // Recupera un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> getUsuarioById(@PathVariable Long id) {
        log.info("GET /usuarios/{} - Recuperando usuario por ID", id);
        Usuario usuario = usuarioService.getUsuarioById(id);

        return ResponseEntity.ok(assembler.toModel(usuario));
    }

    // Crea un nuevo usuario
    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> createUsuario(@Valid @RequestBody Usuario usuario) {
        log.info("POST /usuarios - Creando nuevo usuario: {}", usuario);
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(assembler.toModel(createdUsuario));
    }

    // Actualiza un usuario existente
    @PostMapping("/{id}")
    public ResponseEntity<EntityModel<Usuario>> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        log.info("POST /usuarios/{} - Actualizando usuario: {}", id);
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(
                assembler.toModel(updatedUsuario));
    }

    // Elimina un usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteUsuario(@PathVariable Long id) {
        log.warn("DELETE /usuarios/{} - Eliminando usuario", id);
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "Usuario eliminado",
                1,
                null
            ));
    }
}
