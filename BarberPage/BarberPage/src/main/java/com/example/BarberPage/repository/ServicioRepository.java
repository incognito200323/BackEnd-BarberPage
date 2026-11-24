package com.example.BarberPage.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BarberPage.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    Optional<Servicio> findByNombreServicio(String nombreServicio);
    List<Servicio> findByCategoria(String categoria);
}