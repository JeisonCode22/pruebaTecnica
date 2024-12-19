package com.nequi.application.usecase.impl;

import com.nequi.application.usecase.ISucursal;
import com.nequi.delivery.exception.AlreadyExistsException;
import com.nequi.delivery.exception.NotFoundException;
import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Franquicia;
import com.nequi.domain.model.Sucursal;
import com.nequi.domain.repository.FranquiciaRepository;
import com.nequi.domain.repository.SucursalRepository;
import com.nequi.infrastructure.dto.SucursalSaveDto;
import com.nequi.utils.Messages;
import com.nequi.utils.MessagesExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.nequi.utils.Constants.ZONE_ID;

@Service
@RequiredArgsConstructor
public class SucursalImpl implements ISucursal {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public ApiResponse<Sucursal> saveSucursal(SucursalSaveDto sucursal) {
        try {
            Franquicia franquicia = franquiciaRepository.findByNombre(sucursal.getFranquicia()).orElseThrow(() -> new NotFoundException(MessagesExceptions.NOT_FOUND));

            Sucursal build = Sucursal.builder()
                    .franquicia(franquicia)
                    .nombre(sucursal.getNombre())
                    .build();

            Sucursal save = sucursalRepository.save(build);

            return ApiResponse
                    .<Sucursal>builder()
                    .statusCode(HttpStatus.CREATED.value())
                    .data(save)
                    .message(Messages.SAVE)
                    .timestamp(LocalDateTime.now(ZoneId.of(ZONE_ID)))
                    .build();

        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(MessagesExceptions.ALREADY_EXISTS);
        }
    }

    public ApiResponse<Void> modifiedSucursal(String oldName, String newName) {
        try {
            Sucursal sucursal = sucursalRepository.findByNombre(oldName).orElseThrow(() -> new NotFoundException(MessagesExceptions.NOT_FOUND));

            sucursal.setNombre(newName);

            sucursalRepository.save(sucursal);

            return ApiResponse
                    .<Void>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message(Messages.MODIFIED)
                    .timestamp(LocalDateTime.now(ZoneId.of(ZONE_ID)))
                    .build();

        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(MessagesExceptions.ALREADY_EXISTS);
        }
    }

}
