package com.nequi.application.usecase;

import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Producto;
import com.nequi.infrastructure.dto.ProductSaveDto;
import com.nequi.infrastructure.dto.UpdateProductDto;
import com.nequi.infrastructure.persistence.MaxProjection;

import java.util.List;

public interface IProducto {

    ApiResponse<Producto> saveProducto(ProductSaveDto productSaveDto);

    ApiResponse<Void> deleteProduct(String nameProduct);

    ApiResponse<Producto> updateProduct(UpdateProductDto updateProductDto, String nameProduct);

    ApiResponse<List<MaxProjection>> maxStockForProduct();
}
