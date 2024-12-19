package com.nequi.domain.repository;

import com.nequi.domain.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SucursalRepository extends JpaRepository<Sucursal, UUID> {
    Optional<Sucursal> findByNombre(String nombre);
}
