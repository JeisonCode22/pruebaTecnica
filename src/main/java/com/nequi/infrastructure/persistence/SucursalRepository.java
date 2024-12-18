package com.nequi.infrastructure.persistence;

import com.nequi.domain.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SucursalRepository extends JpaRepository<Sucursal, UUID> {
}
