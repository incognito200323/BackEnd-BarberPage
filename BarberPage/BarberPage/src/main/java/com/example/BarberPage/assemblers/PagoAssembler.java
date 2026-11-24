package com.example.BarberPage.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.BarberPage.DTO.PagoDTO;
import com.example.BarberPage.controller.PagoController;

@Component
public class PagoAssembler implements RepresentationModelAssembler<PagoDTO, EntityModel<PagoDTO>> {

    @Override
    public EntityModel<PagoDTO> toModel(PagoDTO pago) {
        return EntityModel.of(pago,
                linkTo(methodOn(PagoController.class).buscarPorId(pago.getId())).withSelfRel(),
                linkTo(methodOn(PagoController.class).listar()).withRel("pagos"),
                linkTo(methodOn(PagoController.class).actualizar(pago.getId(), null)).withRel("actualizar"),
                linkTo(methodOn(PagoController.class).eliminar(pago.getId())).withRel("eliminar"));
    }
}