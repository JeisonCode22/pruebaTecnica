package com.nequi.application.usecase;

import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Sucursal;
import com.nequi.infrastructure.dto.SucursalSaveDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface ISucursal {
    ApiResponse<Sucursal> saveSucursal(@RequestBody SucursalSaveDto sucursal);

    ApiResponse<Void> modifiedSucursal(String oldName, String newName);
}
