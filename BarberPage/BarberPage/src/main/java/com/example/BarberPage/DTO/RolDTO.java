package com.example.BarberPage.DTO;

import java.util.List;

import lombok.Data;

@Data 
public class RolDTO {
    private Integer id;
    private String nombreRol;
    private List<Integer> usuarios;
}
