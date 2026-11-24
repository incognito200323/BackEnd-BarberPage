package com.example.BarberPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarberPage.DTO.PagoDTO;
import com.example.BarberPage.model.Pago;
import com.example.BarberPage.repository.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<PagoDTO> obtenerPagos() {
        return pagoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PagoDTO buscarPorId(Integer id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado con el id: " + id));
        return convertirADTO(pago);
    }

    public PagoDTO guardar(Pago nuevoPago) {
        Pago pagoGuardado = pagoRepository.save(nuevoPago);
        return convertirADTO(pagoGuardado);
    }

    public Pago actualizarPago(Integer id, Pago pagoActualizado) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el pago con el id: " + id));
        if (pagoActualizado.getMontoTotal() != null) {
            pago.setMontoTotal(pagoActualizado.getMontoTotal());
        }
        if (pagoActualizado.getMetodoPago() != null) {
            pago.setMetodoPago(pagoActualizado.getMetodoPago());
        }
        if (pagoActualizado.getEstadoPago() != null) {
            pago.setEstadoPago(pagoActualizado.getEstadoPago());
        }
        if (pagoActualizado.getFechaPago() != null) {
            pago.setFechaPago(pagoActualizado.getFechaPago());
        }
        if (pagoActualizado.getReserva() != null) {
            pago.setReserva(pagoActualizado.getReserva());
        }
        return pagoRepository.save(pago);
    }

    public String eliminar(Integer id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar, el pago no existe con el id: " + id));
        pagoRepository.delete(pago);
        return "Pago eliminado exitosamente";
    }

    public PagoDTO convertirADTO(Pago pago) {
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setMontoTotal(pago.getMontoTotal());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstadoPago(pago.getEstadoPago());
        dto.setFechaPago(pago.getFechaPago());

        if (pago.getReserva() != null) {
            dto.setReservaId(pago.getReserva().getId());
        }
        return dto;
    }
}