package com.template.ms_customer.infrastructure.adapters.output.mapper;


import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.infrastructure.adapters.output.mongo.document.CustomerDocument;
import org.openapitools.model.BaseCustomerRequest;
import org.openapitools.model.CustomerResponse;
import org.openapitools.model.CustomerType;

public interface CustomerMapper {
    Customer toDomain(CustomerDocument document);
    CustomerDocument toDocument(Customer customer);
    Customer fromGenerate(BaseCustomerRequest request);
    CustomerResponse toCustomerResponse(CustomerDocument request);

    Customer fromGenerateUpdate(BaseCustomerRequest request);

    CustomerType support();
}
