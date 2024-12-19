package com.nequi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID sucursalId;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "franquicia_id", nullable = false)
    private Franquicia franquicia;
}
