package com.nequi.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MaxStockDto {
    private String productoNombre;
    private int productoStock;
    private String sucursalNombre;
    private String franquiciaNombre;
}
