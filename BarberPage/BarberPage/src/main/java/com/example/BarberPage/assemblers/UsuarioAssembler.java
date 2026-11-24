package com.example.BarberPage.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.example.BarberPage.controller.UsuarioController;
import com.example.BarberPage.DTO.UsuarioDTO;
@Component
public class UsuarioAssembler implements RepresentationModelAssembler<UsuarioDTO, EntityModel<UsuarioDTO>> {

    @Override
    public EntityModel<UsuarioDTO> toModel(UsuarioDTO usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).buscarPorId(usuario.getId())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios"),
                linkTo(methodOn(UsuarioController.class).actualizar(usuario.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(UsuarioController.class).eliminar(usuario.getId())).withRel("eliminar"));
    }
}