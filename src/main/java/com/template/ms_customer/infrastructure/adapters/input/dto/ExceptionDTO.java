package com.template.ms_customer.infrastructure.adapters.input.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDTO {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String message;
}
