package com.example.BarberPage.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.example.BarberPage.DTO.RolDTO;
import com.example.BarberPage.controller.RolController;

@Component
public class RolAssembler implements RepresentationModelAssembler<RolDTO, EntityModel<RolDTO>> {

    @Override
    public EntityModel<RolDTO> toModel(RolDTO rol) {
        return EntityModel.of(rol,
                linkTo(methodOn(RolController.class).buscarPorId(rol.getId())).withSelfRel(),
                linkTo(methodOn(RolController.class).listar()).withRel("roles"),
                linkTo(methodOn(RolController.class).actualizar(rol.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(RolController.class).eliminar(rol.getId())).withRel("eliminar"));
    }
}