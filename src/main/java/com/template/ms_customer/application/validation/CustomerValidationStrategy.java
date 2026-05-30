package com.template.ms_customer.application.validation;

import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.domain.record.CustomerValidationKey;
import org.openapitools.model.CustomerType;

public interface CustomerValidationStrategy {
    void validate(Customer customer);
    CustomerValidationKey supports();
}
