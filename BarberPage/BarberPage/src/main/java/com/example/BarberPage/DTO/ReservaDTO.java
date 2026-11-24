package com.example.BarberPage.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.Data;

@Data
public class ReservaDTO {
    private Integer id;
    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private String estado;
    private String comentarios;
    private String usuarioNombre;
    private List<String> servicios;
    private String estadoPago;
}