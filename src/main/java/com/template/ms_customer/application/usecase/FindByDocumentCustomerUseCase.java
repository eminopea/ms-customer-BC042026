package com.template.ms_customer.application.usecase;

import com.template.ms_customer.domain.port.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CustomerResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FindByDocumentCustomerUseCase {
    private final CustomerPort customerPort;

    public Mono<CustomerResponse> executeFindByDocument(
            String documentNumber
    ) {
        return customerPort.findByDocumentNumber(documentNumber);
    }
}
