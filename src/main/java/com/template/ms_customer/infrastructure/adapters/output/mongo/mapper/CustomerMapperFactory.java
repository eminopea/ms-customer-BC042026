package com.template.ms_customer.infrastructure.adapters.output.mongo.mapper;

import com.template.ms_customer.domain.model.Customer;
import lombok.Getter;
import org.openapitools.model.CustomerType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CustomerMapperFactory {
    private final Map<CustomerType, CustomerMapper> mappers;

    public CustomerMapperFactory(List<CustomerMapper> mapperList) {
        this.mappers = mapperList.stream()
                .collect(Collectors.toMap(
                        CustomerMapper::support,
                        Function.identity()
                ));
    }



    public CustomerMapper getMapper(CustomerType type) {
        return Optional.ofNullable(mappers.get(type))
                .orElseThrow(() -> new RuntimeException("No mapper for type: " + type));
    }
}
