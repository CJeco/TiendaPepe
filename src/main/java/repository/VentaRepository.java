package com.tiendapepe.Tiendapepe.repository;

import com.tiendapepe.Tiendapepe.model.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}