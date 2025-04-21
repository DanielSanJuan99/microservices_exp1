package com.project.sumativa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sumativa.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // No need to add any methods here, JpaRepository provides all the necessary CRUD operations
    
}
