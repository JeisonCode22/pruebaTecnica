package com.nequi.application.usecase;

import com.nequi.delivery.response.ApiResponse;
import com.nequi.domain.model.Franquicia;
import org.springframework.web.bind.annotation.RequestBody;

public interface IFranquicia {

    ApiResponse<Franquicia> saveFranquicia(@RequestBody Franquicia franquicia);

    ApiResponse<Void> modifiedFranquicia(String oldName, String newName);
}
