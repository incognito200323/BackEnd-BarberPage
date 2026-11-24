package com.example.BarberPage.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BarberPage.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
    Optional<Pago> findByReservaId(Integer reservaId);
    List<Pago> findByEstadoPago(String estadoPago);
}