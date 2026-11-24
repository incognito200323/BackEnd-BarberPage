package com.example.BarberPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarberPage.DTO.ReservaDTO;
import com.example.BarberPage.model.Reserva;
import com.example.BarberPage.repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<ReservaDTO> obtenerReservas() {
        return reservaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ReservaDTO buscarPorId(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con el id: " + id));
        return convertirADTO(reserva);
    }

    public ReservaDTO guardar(Reserva nuevaReserva) {
        Reserva reservaGuardada = reservaRepository.save(nuevaReserva);
        return convertirADTO(reservaGuardada);
    }

    public Reserva actualizarReserva(Integer id, Reserva reservaActualizada) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra la reserva con el id: " + id));
        if (reservaActualizada.getFechaReserva() != null) {
            reserva.setFechaReserva(reservaActualizada.getFechaReserva());
        }
        if (reservaActualizada.getHoraReserva() != null) {
            reserva.setHoraReserva(reservaActualizada.getHoraReserva());
        }
        if (reservaActualizada.getEstado() != null) {
            reserva.setEstado(reservaActualizada.getEstado());
        }
        if (reservaActualizada.getComentarios() != null) {
            reserva.setComentarios(reservaActualizada.getComentarios());
        }
        if (reservaActualizada.getUsuario() != null) {
            reserva.setUsuario(reservaActualizada.getUsuario());
        }
        return reservaRepository.save(reserva);
    }

    public String eliminar(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar, la reserva no existe con el id: " + id));
        reservaRepository.delete(reserva);
        return "Reserva eliminada exitosamente";
    }

    public ReservaDTO convertirADTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setFechaReserva(reserva.getFechaReserva());
        dto.setHoraReserva(reserva.getHoraReserva());
        dto.setEstado(reserva.getEstado());
        dto.setComentarios(reserva.getComentarios());

        if (reserva.getUsuario() != null) {
            dto.setUsuarioNombre(reserva.getUsuario().getNombreCompleto());
        }
        if (reserva.getDetallesReserva() != null && !reserva.getDetallesReserva().isEmpty()) {
            dto.setServicios(reserva.getDetallesReserva().stream()
                    .filter(detalle -> detalle.getServicio() != null)
                    .map(detalle -> detalle.getServicio().getNombreServicios())
                    .toList());
        }
        if (reserva.getPago() != null) {
            dto.setEstadoPago(reserva.getPago().getEstadoPago());
        }
        return dto;
    }
}