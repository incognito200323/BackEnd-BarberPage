package com.example.BarberPage.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.BarberPage.DTO.ServicioDTO;
import com.example.BarberPage.controller.ServicioController;

@Component
public class ServicioAssembler implements RepresentationModelAssembler<ServicioDTO, EntityModel<ServicioDTO>> {

    @Override
    public EntityModel<ServicioDTO> toModel(ServicioDTO servicio) {
        return EntityModel.of(servicio,
                linkTo(methodOn(ServicioController.class).buscarPorId(servicio.getId())).withSelfRel(),
                linkTo(methodOn(ServicioController.class).listar()).withRel("servicios"),
                linkTo(methodOn(ServicioController.class).actualizar(servicio.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(ServicioController.class).eliminar(servicio.getId())).withRel("eliminar"));
    }
}