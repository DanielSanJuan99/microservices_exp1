package com.project.sumativa;

import com.project.sumativa.model.Usuario;
import com.project.sumativa.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

//SIMULAR CONEXIÓN A LA BASE DE DATOS
@DataJpaTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAndSave() {
        Usuario usuario = new Usuario(2L, "Henrique", 
        LocalDate.parse("1982-08-29"), "doglover82@gmail.com", "Conductor Pet Friendly");

        usuarioRepository.save(usuario);

        Optional<Usuario> found = usuarioRepository.findById(2L);

        assertTrue(found.isPresent());
        assertEquals("Henrique", found.get().getNombre());
    }
    
}
