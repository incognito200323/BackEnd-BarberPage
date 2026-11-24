package com.example.BarberPage.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.BarberPage.DTO.DetalleReservaDTO;
import com.example.BarberPage.controller.DetalleReservaController;

@Component
public class DetalleReservaAssembler implements RepresentationModelAssembler<DetalleReservaDTO, EntityModel<DetalleReservaDTO>> {

    @Override
    public EntityModel<DetalleReservaDTO> toModel(DetalleReservaDTO detalle) {
        return EntityModel.of(detalle,
                linkTo(methodOn(DetalleReservaController.class).buscarPorId(detalle.getId())).withSelfRel(),
                linkTo(methodOn(DetalleReservaController.class).listar()).withRel("detalles-reserva"),
                linkTo(methodOn(DetalleReservaController.class).actualizar(detalle.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(DetalleReservaController.class).eliminar(detalle.getId())).withRel("eliminar"));
    }
}