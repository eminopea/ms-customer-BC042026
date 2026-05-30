package com.template.ms_customer.infrastructure.adapters.input.service;

import com.template.ms_customer.application.usecase.FindByDocumentCustomerUseCase;
import com.template.ms_customer.application.usecase.RegisterCustomerUseCase;
import com.template.ms_customer.application.usecase.SearchCustomerByFiltersUseCase;
import com.template.ms_customer.application.usecase.UpdateCustomerUseCase;
import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.infrastructure.adapters.input.shared.MessageResponse;
import com.template.ms_customer.infrastructure.adapters.output.mongo.mapper.CustomerMapper;
import com.template.ms_customer.infrastructure.adapters.output.mongo.mapper.CustomerMapperFactory;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.CustomersApi;
import org.openapitools.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class CustomersApiDelegateImpl implements CustomersApi {
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final SearchCustomerByFiltersUseCase searchCustomerByFiltersUseCase;
    private final FindByDocumentCustomerUseCase findByDocumentCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    private final CustomerMapperFactory factory;

    @Override
    public Mono<ResponseEntity<ResponseCustomerApi>> createCustomer(
            Mono<BaseCustomerRequest> baseCustomerRequest, ServerWebExchange exchange) {
        return baseCustomerRequest
                .map(request -> {
                    CustomerMapper mapper = factory.getMapper(request.getCustomerType());
                    return mapper.fromGenerate(request);
                })
                .flatMap(registerCustomerUseCase::executeSaveCustomer)
                .map(customer ->
                        ResponseEntity
                                .created(URI.create("/customers/" + customer.getId()))
                                .body(
                                        ResponseCustomerApi.builder()
                                                .code(MessageResponse.CUSTOMER_CREATED_SUCCESS.getCode())
                                                .message(
                                                        String.format(
                                                                MessageResponse.CUSTOMER_CREATED_SUCCESS.getMessage(),
                                                                customer.getDocumentNumber()
                                                        )
                                                )
                                                .build()
                                )
                );
    }

    @Override
    public Mono<ResponseEntity<PagedCustomerResponse>> searchCustomers(
            CustomerStatus status, CustomerProfile profile, String documentNumber,
            Integer page, Integer size, ServerWebExchange exchange) {
        return searchCustomerByFiltersUseCase.execute(
                        status != null ? status.name() : null,
                        profile != null ? profile.name() : null,
                        documentNumber,
                        page != null ? page : 0,
                        size != null ? size : 10
                )
                .map(ResponseEntity::ok);

    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> findByDocument(String documentNumber, ServerWebExchange exchange) {

        return findByDocumentCustomerUseCase.executeFindByDocument(documentNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @Override
    public Mono<ResponseEntity<ResponseCustomerApi>> updateCustomer(
            String customerId, Mono<BaseCustomerRequest> baseCustomerRequest, ServerWebExchange exchange) {

        return baseCustomerRequest
                .map(request -> {
                    CustomerMapper mapper = factory.getMapper(request.getCustomerType());
                    Customer customer = mapper.fromGenerateUpdate(request);
                    customer.setId(customerId);
                    customer.setUpdatedAt(LocalDate.now());
                    return customer;

                })
                .flatMap(customer ->
                        updateCustomerUseCase.executeUpdateCustomer(customerId, customer)
                )
                .map(customer ->
                        ResponseEntity
                                .created(URI.create("/customers/" + customer.getId()))
                                .body(
                                        ResponseCustomerApi.builder()
                                                .code(MessageResponse.CUSTOMER_UPDATE_SUCCESS.getCode())
                                                .message(
                                                        String.format(
                                                                MessageResponse.CUSTOMER_UPDATE_SUCCESS.getMessage(),
                                                                customer.getDocumentNumber()
                                                        )
                                                )
                                                .build()
                                )
                );
    }
}
