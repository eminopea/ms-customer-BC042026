package com.template.ms_customer.application.usecase;

import com.template.ms_customer.application.exception.CustomerAlreadyExistsException;
import com.template.ms_customer.application.shared.MessageException;
import com.template.ms_customer.application.validation.factory.CustomerValidationFactory;
import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.domain.port.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RegisterCustomerUseCase {
    private final CustomerPort customerPort;
    private final CustomerValidationFactory factory;

    public Mono<Customer> executeSaveCustomer(Customer customer) {
        factory.getStrategy(customer.getDocumentType(), customer.getCustomerType()).validate(customer);
        return customerPort.findByDocumentNumber(customer.getDocumentNumber())
                .flatMap(existing ->
                        Mono.<Customer>error(
                                new CustomerAlreadyExistsException(
                                        MessageException.CUSTOMER_ALREADY_EXISTS.format(customer.getDocumentNumber())
                                )

                        )
                )
                .switchIfEmpty(
                        customerPort.registerCustomer(customer)
                );
    }
}
