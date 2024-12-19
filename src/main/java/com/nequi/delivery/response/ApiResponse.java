package com.nequi.delivery.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.nequi.utils.Constants.ZONE_ID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ApiResponse<T> {
    private String message;
    private T data;
    private int statusCode;
    private LocalDateTime timestamp;

    public ApiResponse(String message, T data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now(ZoneId.of(ZONE_ID));
    }
}
