package com.nequi.delivery.controller;

import com.nequi.application.usecase.ISucursal;
import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Sucursal;
import com.nequi.infrastructure.dto.SucursalSaveDto;
import com.nequi.utils.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SucursalController {
    private final ISucursal iSucursal;

    @PostMapping(Endpoints.SUCURSAL)
    public ResponseEntity<ApiResponse<Sucursal>> saveSucursal(@RequestBody SucursalSaveDto saveDto) {

        ApiResponse<Sucursal> sucursalApiResponse = iSucursal.saveSucursal(saveDto);

        return ResponseEntity.status(sucursalApiResponse.getStatusCode()).body(sucursalApiResponse);
    }

    @PatchMapping(Endpoints.SUCURSAL)
    public ResponseEntity<ApiResponse<Void>> modifiedSucursal(@RequestParam String oldName, @RequestParam String newName) {

        ApiResponse<Void> voidApiResponse = iSucursal.modifiedSucursal(oldName, newName);

        return ResponseEntity.status(voidApiResponse.getStatusCode()).body(voidApiResponse);
    }
}
