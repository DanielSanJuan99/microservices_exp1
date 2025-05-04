package com.project.sumativa.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.project.sumativa.controller.UsuarioController;
import com.project.sumativa.model.Usuario;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(
                usuario, // Entidad original

                // Enlace para eliminar el usuario (GET /usuarios)
                linkTo(methodOn(UsuarioController.class)
                        .getAllUsuarios())
                        .withRel("all"),

                // Enlace detalle del usuario (GET /usuarios/{id})
                linkTo(methodOn(UsuarioController.class)
                        .getUsuarioById(usuario.getId()))
                        .withSelfRel(),

                linkTo(methodOn(UsuarioController.class)
                        .updateUsuario(usuario.getId(), null))
                        .withRel("update"),
                
                linkTo(methodOn(UsuarioController.class)
                        .deleteUsuario(usuario.getId()))
                        .withRel("delete")); // Link para actualizar el usuario existente;
    }
}
