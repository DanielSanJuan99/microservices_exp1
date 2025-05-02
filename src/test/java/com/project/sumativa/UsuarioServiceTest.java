package com.project.sumativa;

import org.springframework.data.domain.Sort;

import com.project.sumativa.service.UsuarioService;
import com.project.sumativa.model.Usuario;
import com.project.sumativa.exception.UsuarioNotFound;
import com.project.sumativa.repository.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UsuarioServiceTest {
    
    private UsuarioService usuarioService;
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    // Emulamos el comportamiento del método getAllUsuarios() de la clase UsuarioService
    @Test
    public void testGetAllUsuarios() {
        // Datos de prueba
        Usuario u1 = new Usuario(1L, "Juan", LocalDate.parse("1999-01-08"), "dsanjuan1999@gmail.com", "Usuario c/mascota");
        Usuario u2 = new Usuario(2L, "Henrique", LocalDate.parse("1982-08-29"), "doglover82@gmail.com", "Consuctor Pet Friendly");

        List<Usuario> usuarios = Arrays.asList(u1, u2);
        when(usuarioRepository.findAll(Sort.by("id"))).thenReturn(usuarios);

        assertEquals(2, usuarios.size());
        assertEquals("Juan", usuarios.get(0).getNombre());
    }

    @Test
    public void testGetUsuarioById() {

        Usuario u1 = new Usuario(1L, "Juan", LocalDate.parse("1999-01-08"), "dsanjuan1999@gmail.com", "Usuario c/mascota");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(u1));

        Usuario usuario = usuarioService.getUsuarioById(1L);
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Usuario c/mascota", usuario.getRol());
    }

    @Test
    public void testGetUsuarioById_NotFound () {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFound.class, () -> {
            usuarioService.getUsuarioById(99L);
        });

    }

}
