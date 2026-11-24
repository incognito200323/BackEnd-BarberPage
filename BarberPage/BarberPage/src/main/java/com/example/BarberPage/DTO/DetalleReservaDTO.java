package com.example.BarberPage.DTO;

import lombok.Data;

@Data
public class DetalleReservaDTO {
    private Integer id;
    private Integer precioUnitario;
    private Integer reservaId;
    private String servicioNombre;
}