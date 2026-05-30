package com.template.ms_customer.domain.port;

import com.template.ms_customer.domain.model.Customer;
import org.openapitools.model.CustomerResponse;
import org.openapitools.model.PagedCustomerResponse;
import reactor.core.publisher.Mono;

public interface CustomerPort {
    Mono<Customer> registerCustomer(Customer customer);
    Mono<CustomerResponse> findByDocumentNumber(String document);
    Mono<PagedCustomerResponse> search(
            String status,
            String profile,
            String documentNumber,
            int page,
            int size
    );
    Mono<Customer> findById(String id);
    Mono<Customer> updateCustomer(Customer customer);

}
