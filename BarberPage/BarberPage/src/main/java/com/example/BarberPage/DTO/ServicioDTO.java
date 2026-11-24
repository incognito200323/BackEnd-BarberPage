package com.example.BarberPage.DTO;

import lombok.Data;

@Data
public class ServicioDTO {  
    private Integer id;
    private String nombreServicio;
    private String descripcion;
    private Integer precio;
    private String categoria;
}