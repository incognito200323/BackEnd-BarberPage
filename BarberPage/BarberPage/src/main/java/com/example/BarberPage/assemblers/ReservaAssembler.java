package com.example.BarberPage.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.BarberPage.DTO.ReservaDTO;
import com.example.BarberPage.controller.ReservaController;

@Component
public class ReservaAssembler implements RepresentationModelAssembler<ReservaDTO, EntityModel<ReservaDTO>> {

    @Override
    public EntityModel<ReservaDTO> toModel(ReservaDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ReservaController.class).buscarPorId(dto.getId())).withSelfRel(),
                linkTo(methodOn(ReservaController.class).listar()).withRel("reservas"),
                linkTo(methodOn(ReservaController.class).actualizar(dto.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(ReservaController.class).eliminar(dto.getId())).withRel("eliminar"));
    }
}