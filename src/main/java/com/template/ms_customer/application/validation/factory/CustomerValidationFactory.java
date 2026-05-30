package com.template.ms_customer.application.validation.factory;

import com.template.ms_customer.application.validation.CustomerValidationStrategy;
import com.template.ms_customer.domain.record.CustomerValidationKey;
import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CustomerValidationFactory {

    private final Map<CustomerValidationKey, CustomerValidationStrategy> strategies;

    public CustomerValidationFactory(List<CustomerValidationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        CustomerValidationStrategy::supports,
                        Function.identity()
                ));
    }


    public CustomerValidationStrategy getStrategy(
            DocumentType documentType,
            CustomerType customerType
    ) {
        CustomerValidationKey key = new CustomerValidationKey(customerType, documentType);
        return Optional.ofNullable(strategies.get(key))
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                String.format("El tipo de cliente %s no puede tener %s", key.customerType(), key.documentType())

                        )
                );
    }

}
