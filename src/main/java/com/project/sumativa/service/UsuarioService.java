package com.project.sumativa.service;

import com.project.sumativa.model.Usuario;
import com.project.sumativa.repository.UsuarioRepository;
import com.project.sumativa.exception.UsuarioNotFound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;
    
    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        log.debug("Servicio: getAllUsuarios()");
        return repo.findAll(Sort.by("id")
            .ascending());
    }

    // Obtener usuario por ID
    public Usuario getUsuarioById(Long id) {
        log.debug("Servicio: getUsuarioById({})", id);
        return repo.findById(id)
            .orElseThrow(() -> new UsuarioNotFound(id));
    }

    // Crear usuario
    public Usuario createUsuario(Usuario usuario) {
        log.debug("Servicio: createUsuario({})", usuario);

        if (repo.existsById(usuario.getId())) {
            log.error("El usuario con ID {} ya existe", usuario.getId());
            throw new IllegalArgumentException("El ID de esta usuario ya existe" + usuario.getId());
        }

        return repo.save(usuario);
    }

    // Actualizar usuario
    public Usuario updateUsuario(Long id, Usuario usuarioUpdt) {
        log.debug("Servicio: updateUsuario({}, {})", id, usuarioUpdt.getNombre());

        Usuario usuarioExiste = repo.findById(id)
            .orElseThrow(() -> new UsuarioNotFound(id));

        usuarioExiste.setNombre(usuarioUpdt.getNombre());
        usuarioExiste.setEmail(usuarioUpdt.getEmail());
        usuarioExiste.setFhaNacimiento(usuarioUpdt.getFhaNacimiento());
        usuarioExiste.setRol(usuarioUpdt.getRol());

        usuarioUpdt.setId(id);
        return repo.save(usuarioUpdt);
    }

    // Eliminar usuario
    public void deleteUsuario(Long id) {
        log.debug("Servicio: deleteUsuario({})", id);
        Usuario usuario = repo.findById(id)
            .orElseThrow(() -> new UsuarioNotFound(id));
        repo.delete(usuario);
    }
}
