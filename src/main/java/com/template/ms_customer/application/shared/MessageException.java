package com.template.ms_customer.application.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum MessageException {

    CUSTOMER_ALREADY_EXISTS("00", "Cliente %s se encuentra duplicado."),

    PERSONAL_INVALID_EMAIL("P01", "Email inválido."),
    PERSONAL_INVALID_PHONE("P02", "Teléfono inválido."),
    PERSONAL_UNDERAGE("P03", "El cliente debe ser mayor de edad."),
    PERSONAL_INVALID_DNI("P04", "DNI inválido: %s ."),

    BUSINESS_INVALID_RUC("B01", "RUC inválido: %s ."),
    BUSINESS_INVALID_NAME("B02", "Razón social inválida."),
    BUSINESS_INVALID_EMAIL("B03", "Email de empresa inválido."),
    BUSINESS_INVALID_PHONE("B04", "Teléfono de empresa inválido.");

    private final String code;
    private final String message;

    public String format(Object... args) {
        return String.format(this.message, args);
    }
}
