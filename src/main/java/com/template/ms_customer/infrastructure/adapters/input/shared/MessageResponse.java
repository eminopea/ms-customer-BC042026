package com.template.ms_customer.infrastructure.adapters.input.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageResponse {

    CUSTOMER_CREATED_SUCCESS("00", "Cliente con DNI: %s registrado exitosamente."),
    CUSTOMER_UPDATE_SUCCESS("00", "Cliente con DNI: %s actualizado exitosamente.");

    private final String code;
    private final String message;
}

