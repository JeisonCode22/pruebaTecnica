package com.nequi.application.usecase.impl;

import com.nequi.application.usecase.IProducto;
import com.nequi.delivery.exception.AlreadyExistsException;
import com.nequi.delivery.exception.NotFoundException;
import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Producto;
import com.nequi.domain.model.Sucursal;
import com.nequi.domain.repository.ProductoRepository;
import com.nequi.domain.repository.SucursalRepository;
import com.nequi.infrastructure.dto.ProductSaveDto;
import com.nequi.infrastructure.dto.UpdateProductDto;
import com.nequi.infrastructure.persistence.MaxProjection;
import com.nequi.utils.Messages;
import com.nequi.utils.MessagesExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

import static com.nequi.utils.Constants.ZONE_ID;

@Service
@RequiredArgsConstructor
public class ProductoImpl implements IProducto {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public ApiResponse<Producto> saveProducto(ProductSaveDto productSaveDto) {
        try {
            Sucursal sucursal = sucursalRepository.findByNombre(productSaveDto.getSucursal()).orElseThrow(() -> new NotFoundException(MessagesExceptions.NOT_FOUND));

            Producto producto = Producto
                    .builder()
                    .nombre(productSaveDto.getProducto())
                    .sucursal(sucursal)
                    .build();

            Producto save = productoRepository.save(producto);

            return ApiResponse.<Producto>builder()
                    .statusCode(HttpStatus.CREATED.value())
                    .data(save)
                    .message(Messages.SAVE)
                    .timestamp(LocalDateTime.now(ZoneId.of(ZONE_ID)))
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(MessagesExceptions.ALREADY_EXISTS);
        }
    }

    public ApiResponse<Void> deleteProduct(String nameProduct) {

        Producto producto = productoRepository.findByNombre(nameProduct).orElseThrow(() -> new NotFoundException(MessagesExceptions.NOT_FOUND));
        productoRepository.delete(producto);

        return ApiResponse.<Void>builder()
                .message(Messages.DELETE)
                .statusCode(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now(ZoneId.of(ZONE_ID)))
                .build();
    }

    public ApiResponse<Producto> updateProduct(UpdateProductDto updateProductDto, String nameProduct) {
        try {
            Producto producto = productoRepository.findByNombre(nameProduct).orElseThrow(() -> new NotFoundException(MessagesExceptions.NOT_FOUND));

            producto.setStock(updateProductDto.getStock() != producto.getStock() && updateProductDto.getStock() != 0 ? updateProductDto.getStock() : producto.getStock());

            producto.setNombre(Objects.nonNull(updateProductDto.getNombre()) ? updateProductDto.getNombre() : producto.getNombre());

            Producto save = productoRepository.save(producto);

            return ApiResponse.<Producto>builder()
                    .statusCode(HttpStatus.CREATED.value())
                    .data(save)
                    .message(Messages.SAVE)
                    .timestamp(LocalDateTime.now(ZoneId.of(ZONE_ID)))
                    .build();

        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(MessagesExceptions.ALREADY_EXISTS);
        }
    }

    public ApiResponse<List<MaxProjection>> maxStockForProduct() {

        List<MaxProjection> productoSucursalFranquicia = productoRepository.findProductoSucursalFranquicia();

        return ApiResponse
                .<List<MaxProjection>>builder()
                .message(Messages.OBTAIN)
                .statusCode(HttpStatus.OK.value())
                .data(productoSucursalFranquicia)
                .timestamp(LocalDateTime.now(ZoneId.of(ZONE_ID)))
                .build();


    }
}

