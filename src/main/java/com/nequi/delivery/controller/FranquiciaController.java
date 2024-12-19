package com.nequi.delivery.controller;

import com.nequi.application.usecase.IFranquicia;
import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Franquicia;
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
public class FranquiciaController {
    private final IFranquicia iFranquicia;

    @PostMapping(Endpoints.FRANQUICIA)
    public ResponseEntity<ApiResponse<Franquicia>> saveFranquicia(@RequestBody Franquicia franquicia) {

        ApiResponse<Franquicia> franquiciaApiResponse = iFranquicia.saveFranquicia(franquicia);

        return ResponseEntity.status(franquiciaApiResponse.getStatusCode()).body(franquiciaApiResponse);
    }

    @PatchMapping(Endpoints.FRANQUICIA)
    public ResponseEntity<ApiResponse<Void>> modifiedSucursal(@RequestParam String oldName, @RequestParam String newName) {

        ApiResponse<Void> voidApiResponse = iFranquicia.modifiedFranquicia(oldName, newName);

        return ResponseEntity.status(voidApiResponse.getStatusCode()).body(voidApiResponse);
    }
}
