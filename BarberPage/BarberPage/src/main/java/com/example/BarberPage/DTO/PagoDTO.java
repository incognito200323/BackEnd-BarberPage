package com.example.BarberPage.DTO;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PagoDTO {
    private Integer id;
    private Integer montoTotal;
    private String metodoPago;
    private String estadoPago;
    private LocalDateTime fechaPago;
    private Integer reservaId;
    
}