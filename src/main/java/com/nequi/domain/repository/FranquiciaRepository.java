package com.nequi.domain.repository;

import com.nequi.domain.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface FranquiciaRepository extends JpaRepository<Franquicia, UUID> {
    Optional<Franquicia> findByNombre(String nombre);
}
