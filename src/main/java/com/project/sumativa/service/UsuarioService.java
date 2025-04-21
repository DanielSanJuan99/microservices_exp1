package com.project.sumativa.service;

import com.project.sumativa.model.Usuario;
import com.project.sumativa.repository.UsuarioRepository;
import com.project.sumativa.exception.UsuarioNotFound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;
    
    public List<Usuario> getAllUsuarios() {
        return repo.findAll(Sort.by("id")
            .ascending());
    }

    public Usuario getUsuarioById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new UsuarioNotFound(id));
    }

}
