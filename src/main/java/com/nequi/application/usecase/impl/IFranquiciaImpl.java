package com.nequi.application.usecase.impl;

import com.nequi.application.usecase.IFranquicia;
import com.nequi.delivery.exception.AlreadyExistsException;
import com.nequi.delivery.exception.NotFoundException;
import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Franquicia;
import com.nequi.domain.repository.FranquiciaRepository;
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
public class IFranquiciaImpl implements IFranquicia {
    private final FranquiciaRepository franquiciaRepository;

    public ApiResponse<Franquicia> saveFranquicia(Franquicia franquicia) {
        try {
            Franquicia save = franquiciaRepository.save(franquicia);

            return ApiResponse
                    .<Franquicia>builder()
                    .statusCode(HttpStatus.CREATED.value())
                    .data(save)
                    .message(Messages.SAVE)
                    .timestamp(LocalDateTime.now(ZoneId.of(ZONE_ID)))
                    .build();

        } catch (DataIntegrityViolationException e) {
            throw new AlreadyExistsException(MessagesExceptions.ALREADY_EXISTS);
        }
    }

    public ApiResponse<Void> modifiedFranquicia(String oldName, String newName) {
        try {
            Franquicia franquicia = franquiciaRepository.findByNombre(oldName).orElseThrow(() -> new NotFoundException(MessagesExceptions.NOT_FOUND));

            franquicia.setNombre(newName);

            franquiciaRepository.save(franquicia);

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
