package com.nequi.infrastructure.persistence;

import com.nequi.domain.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface FranquiciaRepository extends JpaRepository<Franquicia, UUID> {
    Optional<Franquicia> findByNombre(String nombre);
}
