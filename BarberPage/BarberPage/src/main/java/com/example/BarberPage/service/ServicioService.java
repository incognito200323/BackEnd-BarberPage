package com.example.BarberPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarberPage.DTO.ServicioDTO;
import com.example.BarberPage.model.Servicio;
import com.example.BarberPage.repository.ServicioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<ServicioDTO> obtenerServicios() {
        return servicioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ServicioDTO buscarPorId(Integer id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con el id: " + id));
        return convertirADTO(servicio);
    }

    public ServicioDTO guardar(Servicio nuevoServicio) {
        Servicio servicioGuardado = servicioRepository.save(nuevoServicio);
        return convertirADTO(servicioGuardado);
    }

    public Servicio actualizarServicio(Integer id, Servicio servicioActualizado) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el servicio con el id: " + id));
        if (servicioActualizado.getNombreServicios() != null) {
            servicio.setNombreServicios(servicioActualizado.getNombreServicios());
        }
        if (servicioActualizado.getDescripcion() != null) {
            servicio.setDescripcion(servicioActualizado.getDescripcion());
        }
        if (servicioActualizado.getPrecio() != null) {
            servicio.setPrecio(servicioActualizado.getPrecio());
        }
        if (servicioActualizado.getCategoria() != null) {
            servicio.setCategoria(servicioActualizado.getCategoria());
        }
        return servicioRepository.save(servicio);
    }

    public String eliminar(Integer id) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar, el servicio no existe con el id: " + id));
        servicioRepository.delete(servicio);
        return "Servicio eliminado exitosamente";
    }

    public ServicioDTO convertirADTO(Servicio servicio) {
        ServicioDTO dto = new ServicioDTO();
        dto.setId(servicio.getId());
        dto.setNombreServicio(servicio.getNombreServicios());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setPrecio(servicio.getPrecio());
        dto.setCategoria(servicio.getCategoria());
        return dto;
    }
}