package com.example.BarberPage.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "La fecha de reserva es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaReserva;

    @NotNull(message = "La hora de reserva es obligatoria")
    @Column(nullable = false)
    private LocalTime horaReserva;

    @NotBlank(message = "El estado de la reserva es obligatorio")
    @Size(max = 30, message = "El estado debe tener un máximo de 30 caracteres")
    @Column(nullable = false, length = 30)
    private String estado;

    @Size(max = 500, message = "Los comentarios deben tener un máximo de 500 caracteres")
    @Column(length = 500)
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DetalleReserva> detallesReserva;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Pago pago;
}