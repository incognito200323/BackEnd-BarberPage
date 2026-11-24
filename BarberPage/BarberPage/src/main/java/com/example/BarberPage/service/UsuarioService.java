package com.example.BarberPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BarberPage.DTO.UsuarioDTO;
import com.example.BarberPage.model.Usuario;
import com.example.BarberPage.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public UsuarioDTO buscarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + id));
        return convertirADTO(usuario);
    }

    public UsuarioDTO guardar(Usuario nuevoUsuario) {
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        return convertirADTO(usuarioGuardado);
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encuentra el usuario con el id: " + id));
        if (usuarioActualizado.getNombreCompleto() != null) {
            usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
        }
        if (usuarioActualizado.getEmail() != null) {
            usuario.setEmail(usuarioActualizado.getEmail());
        }
        if (usuarioActualizado.getTelefono() != null) {
            usuario.setTelefono(usuarioActualizado.getTelefono());
        }
        if (usuarioActualizado.getContrasena() != null) {
            usuario.setContrasena(usuarioActualizado.getContrasena());
        }
        if (usuarioActualizado.getRol() != null) {
            usuario.setRol(usuarioActualizado.getRol());
        }
        return usuarioRepository.save(usuario);
    }

    public String eliminar(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se puede eliminar, el usuario no existe con el id: " + id));
        usuarioRepository.delete(usuario);
        return "Usuario eliminado exitosamente";
    }

    public UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());

        if (usuario.getRol() != null) {
            dto.setRolNombre(usuario.getRol().getNombreRol());
        }
        if (usuario.getReservas() != null && !usuario.getReservas().isEmpty()) {
            dto.setReservas(usuario.getReservas().stream()
                    .map(reserva -> reserva.getId())
                    .toList());
        }
        return dto;
    }
}