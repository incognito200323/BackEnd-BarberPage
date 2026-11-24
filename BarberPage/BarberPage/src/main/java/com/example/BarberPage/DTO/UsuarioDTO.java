package com.example.BarberPage.DTO;

import java.util.List;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Integer id;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String rolNombre;
    private List<Integer> reservas;
}
