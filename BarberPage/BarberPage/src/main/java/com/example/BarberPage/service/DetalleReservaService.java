package com.example.BarberPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarberPage.DTO.DetalleReservaDTO;
import com.example.BarberPage.model.DetalleReserva;
import com.example.BarberPage.repository.DetalleReservaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetalleReservaService {

    @Autowired
    private DetalleReservaRepository detalleReservaRepository;

    public List<DetalleReservaDTO> obtenerDetalles() {
        return detalleReservaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public DetalleReservaDTO buscarPorId(Integer id) {
        DetalleReserva detalle = detalleReservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de reserva no encontrado con el id: " + id));
        return convertirADTO(detalle);
    }

    public DetalleReservaDTO guardar(DetalleReserva nuevoDetalle) {
        DetalleReserva detalleGuardado = detalleReservaRepository.save(nuevoDetalle);
        return convertirADTO(detalleGuardado);
    }

    public DetalleReserva actualizarDetalle(Integer id, DetalleReserva detalleActualizado) {
        DetalleReserva detalle = detalleReservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el detalle de reserva con el id: " + id));
        if (detalleActualizado.getPrecioUnitario() != null) {
            detalle.setPrecioUnitario(detalleActualizado.getPrecioUnitario());
        }
        if (detalleActualizado.getReserva() != null) {
            detalle.setReserva(detalleActualizado.getReserva());
        }
        if (detalleActualizado.getServicio() != null) {
            detalle.setServicio(detalleActualizado.getServicio());
        }
        return detalleReservaRepository.save(detalle);
    }

    public String eliminar(Integer id) {
        DetalleReserva detalle = detalleReservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar, el detalle de reserva no existe con el id: " + id));
        detalleReservaRepository.delete(detalle);
        return "Detalle de reserva eliminado exitosamente";
    }

    public DetalleReservaDTO convertirADTO(DetalleReserva detalle) {
        DetalleReservaDTO dto = new DetalleReservaDTO();
        dto.setId(detalle.getId());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());

        if (detalle.getReserva() != null) {
            dto.setReservaId(detalle.getReserva().getId());
        }
        if (detalle.getServicio() != null) {
            dto.setServicioNombre(detalle.getServicio().getNombreServicios());
        }
        return dto;
    }
}