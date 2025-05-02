package com.project.sumativa;

import com.project.sumativa.controller.UsuarioController;
import com.project.sumativa.hateoas.UsuarioModelAssembler;
import com.project.sumativa.model.Usuario;
import com.project.sumativa.service.UsuarioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private UsuarioService usuarioService;

    @SuppressWarnings("removal")
    @MockBean
    private UsuarioModelAssembler usuarioModelAssembler;

    @Test
    @WithMockUser(username = "admin", password = "admin123", roles = {"ADMIN"})

    public void testGetUsuarioById() throws Exception {
        Usuario usuario = new Usuario(1L, "Juan", LocalDate.parse("1999-01-08"), "dsanjuan1999@gmail.com", "Usuario c/mascota");

        EntityModel<Usuario> usuarioModel = EntityModel.of(usuario,
            linkTo(methodOn(UsuarioController.class)
                    .getAllUsuarios())
                    .withRel("all"),

            linkTo(methodOn(UsuarioController.class)
                    .getUsuarioById(usuario.getId()))
                    .withSelfRel(),

            linkTo(methodOn(UsuarioController.class)
                    .createUsuario(usuario))
                    .withRel("usuario"),

            linkTo(methodOn(UsuarioController.class)
                    .updateUsuario(usuario.getId(), usuario))
                    .withRel("update"),

            linkTo(methodOn(UsuarioController.class)
                    .deleteUsuario(usuario.getId()))
                    .withRel("delete"));

        when(usuarioService.getUsuarioById(1L)).thenReturn(usuario);
        when(usuarioModelAssembler.toModel(usuario)).thenReturn(usuarioModel);

        mockMvc.perform(get("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.fechaNacimiento").value("1999-01-08"))
                .andExpect(jsonPath("$.email").value("dsanjuan1999@gmail.com"))
                .andExpect(jsonPath("$.rol").value("Usuario c/mascota"))
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.delete.href").exists())
                .andExpect(jsonPath("$._links.update.href").exists());

    }
    
}
