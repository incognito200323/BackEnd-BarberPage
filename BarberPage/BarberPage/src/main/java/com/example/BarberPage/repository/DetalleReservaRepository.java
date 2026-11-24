package com.example.BarberPage.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BarberPage.model.DetalleReserva;

@Repository
public interface DetalleReservaRepository extends JpaRepository<DetalleReserva, Integer> {
    List<DetalleReserva> findByReservaId(Integer reservaId);
    List<DetalleReserva> findByServicioId(Integer servicioId);
}