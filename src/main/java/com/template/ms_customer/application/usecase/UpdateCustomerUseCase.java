package com.template.ms_customer.application.usecase;

import com.template.ms_customer.application.exception.CustomerAlreadyExistsException;
import com.template.ms_customer.application.exception.CustomerNotFoundException;
import com.template.ms_customer.application.shared.MessageException;
import com.template.ms_customer.application.validation.factory.CustomerValidationFactory;
import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.domain.port.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UpdateCustomerUseCase {
    private final CustomerPort customerPort;
    private final CustomerValidationFactory factory;

    public Mono<Customer> executeUpdateCustomer(String id, Customer customer) {
        factory.getStrategy(
                customer.getDocumentType(),
                customer.getCustomerType()
        ).validate(customer);

        return customerPort.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomerNotFoundException(
                                "Cliente no existe con id: " + id
                        ))
                )
                .flatMap(existing -> {
                    customer.setCreatedAt(existing.getCreatedAt());
                    if (!existing.getDocumentNumber()
                            .equals(customer.getDocumentNumber())) {

                        return customerPort.findByDocumentNumber(customer.getDocumentNumber())
                                .flatMap(dup ->
                                        Mono.<Customer>error(
                                                new CustomerAlreadyExistsException(
                                                        MessageException.CUSTOMER_ALREADY_EXISTS
                                                                .format(customer.getDocumentNumber())
                                                )
                                        )
                                )
                                .switchIfEmpty(
                                        customerPort.updateCustomer(customer)
                                );
                    }

                    return customerPort.updateCustomer(customer);
                });
    }
}
