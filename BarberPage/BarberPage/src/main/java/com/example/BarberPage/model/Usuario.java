package com.example.BarberPage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Entity 
@Table(name = "usuarios")
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNombre;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombres;

    @NotBlank (message = "El Email Es obligatorio")
    @Size(max = 100, message = "El Email debe tener un maximo de 100 caracteres")
    private String email;

    @NotBlank (message = "El telefono es obligatorio.")
    @Size (min = 9, max = 12, message = "El telefono debe tener minimo 9 caracteres y maximo 12")
    @Column (nullable = false, length = 12)
    private String telefono;

    @NotBlank (message = "La contraseña es obligatoria")
    @Size(min = 10 ,message = "La contraseña debe tener un minimo de 10 caracteres")
    private String contrasena;

    @Column(length = 20)
    private String rol = "cliente";
    
}
