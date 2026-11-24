package com.example.BarberPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarberPage.DTO.RolDTO;
import com.example.BarberPage.model.Rol;
import com.example.BarberPage.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<RolDTO> obtenerRoles() {
        return rolRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public RolDTO buscarPorId(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con el id: " + id));
        return convertirADTO(rol);
    }

    public RolDTO guardar(Rol nuevoRol) {
        Rol rolGuardado = rolRepository.save(nuevoRol);
        return convertirADTO(rolGuardado);
    }

    public Rol actualizarRol(Integer id, Rol rolActualizado) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el rol con el id: " + id));
        if (rolActualizado.getNombreRol() != null) {
            rol.setNombreRol(rolActualizado.getNombreRol());
        }
        return rolRepository.save(rol);
    }

    public String eliminar(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar, el rol no existe con el id: " + id));
        rolRepository.delete(rol);
        return "Rol eliminado exitosamente";
    }

    public RolDTO convertirADTO(Rol rol) {
        RolDTO dto = new RolDTO();
        dto.setId(rol.getId());
        dto.setNombreRol(rol.getNombreRol());

        if (rol.getUsuarios() != null && !rol.getUsuarios().isEmpty()) {
            dto.setUsuarios(rol.getUsuarios().stream()
                    .map(usuario -> usuario.getId())
                    .toList());
        }
        return dto;
    }
}