package com.example.BarberPage.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BarberPage.DTO.DetalleReservaDTO;
import com.example.BarberPage.assemblers.DetalleReservaAssembler;
import com.example.BarberPage.model.DetalleReserva;
import com.example.BarberPage.service.DetalleReservaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/detalles-reserva")
public class DetalleReservaController {

    @Autowired
    private DetalleReservaService detalleReservaService;

    @Autowired
    private DetalleReservaAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> listar() {
        List<DetalleReservaDTO> detalles = detalleReservaService.obtenerDetalles();
        if (detalles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<EntityModel<DetalleReservaDTO>> detalleModels = detalles.stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<DetalleReservaDTO>> collectionModel = CollectionModel.of(detalleModels,
                linkTo(methodOn(DetalleReservaController.class).listar()).withSelfRel());

        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
        try {
            DetalleReservaDTO dto = detalleReservaService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> registrar(@Valid @RequestBody DetalleReserva nuevoDetalle) {
        try {
            DetalleReservaDTO dto = detalleReservaService.guardar(nuevoDetalle);
            return new ResponseEntity<>(assembler.toModel(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @Valid @RequestBody DetalleReserva detalleActualizado) {
        try {
            DetalleReserva detalleEditado = detalleReservaService.actualizarDetalle(id, detalleActualizado);
            DetalleReservaDTO dto = detalleReservaService.convertirADTO(detalleEditado);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            String mensaje = detalleReservaService.eliminar(id);
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}