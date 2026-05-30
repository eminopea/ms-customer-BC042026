package com.template.ms_customer.application.usecase;

import com.template.ms_customer.domain.port.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.PagedCustomerResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SearchCustomerByFiltersUseCase {
    private final CustomerPort customerPort;
    public Mono<PagedCustomerResponse> execute(
            String status,
            String profile,
            String documentNumber,
            int page,
            int size
    ) {

        int safePage = Math.max(page, 0);
        int safeSize = size > 0 ? size : 10;

        return customerPort.search(
                status,
                profile,
                documentNumber,
                safePage,
                safeSize
        );
    }

}
