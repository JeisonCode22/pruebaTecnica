package com.nequi.delivery.exception;

import com.nequi.delivery.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleAlReadyException(AlreadyExistsException ex) {

        ApiResponse<Void> voidApiResponse = new ApiResponse<>(ex.getMessage(), null, HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(voidApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NotFoundException ex) {

        ApiResponse<Void> voidApiResponse = new ApiResponse<>(ex.getMessage(), null, HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(voidApiResponse, HttpStatus.NOT_FOUND);
    }
}
