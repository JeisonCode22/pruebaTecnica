package com.nequi.domain.repository;

import com.nequi.domain.model.Producto;
import com.nequi.infrastructure.persistence.MaxProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, UUID> {
    Optional<Producto> findByNombre(String nombre);

    @Query(value = "SELECT " +
            "p.nombre AS producto_nombre, " +
            "p.stock AS max_stock, " +
            "s.nombre AS sucursal_nombre, " +
            "f.nombre AS franquicia_nombre " +
            "FROM producto p " +
            "JOIN sucursal s ON p.sucursal_id = s.sucursal_id " +
            "JOIN franquicia f ON s.franquicia_id = f.franquicia_id " +
            "WHERE p.stock = ( " +
            "    SELECT MAX(p2.stock) " +
            "    FROM producto p2 " +
            "    WHERE p2.sucursal_id = p.sucursal_id " +
            ") " +
            "ORDER BY f.nombre, s.nombre",
            nativeQuery = true)
    List<MaxProjection> findProductoSucursalFranquicia();

}
