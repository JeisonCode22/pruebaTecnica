package com.nequi.delivery.controller;

import com.nequi.application.usecase.IProducto;
import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Producto;
import com.nequi.infrastructure.dto.ProductSaveDto;
import com.nequi.infrastructure.dto.UpdateProductDto;
import com.nequi.infrastructure.persistence.MaxProjection;
import com.nequi.utils.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductoController {

    private final IProducto iProducto;


    @PostMapping(Endpoints.PRODUCTO)
    public ResponseEntity<ApiResponse<Producto>> saveProducto(@RequestBody ProductSaveDto producto) {

        ApiResponse<Producto> productoApiResponse = iProducto.saveProducto(producto);

        return ResponseEntity.status(productoApiResponse.getStatusCode()).body(productoApiResponse);
    }

    @DeleteMapping(Endpoints.PRODUCTO)
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@RequestParam String productName) {

        ApiResponse<Void> voidApiResponse = iProducto.deleteProduct(productName);

        return ResponseEntity.status(voidApiResponse.getStatusCode()).body(voidApiResponse);
    }

    @PutMapping(Endpoints.UPDATE_PRODUCTO)
    public ResponseEntity<ApiResponse<Producto>> updateStock(@PathVariable String nameProduct, @RequestBody UpdateProductDto updateProductDto) {

        ApiResponse<Producto> productoApiResponse = iProducto.updateProduct(updateProductDto, nameProduct);

        return ResponseEntity.status(productoApiResponse.getStatusCode()).body(productoApiResponse);
    }

    @GetMapping(Endpoints.COUNT_PRODUCTS)
    public ResponseEntity<ApiResponse<List<MaxProjection>>> maxStock() {

        ApiResponse<List<MaxProjection>> listApiResponse = iProducto.maxStockForProduct();

        return ResponseEntity.status(listApiResponse.getStatusCode()).body(listApiResponse);

    }

}
