package com.template.ms_customer.application.validation.impl;

import com.template.ms_customer.application.shared.MessageException;
import com.template.ms_customer.application.shared.RegexConstants;
import com.template.ms_customer.application.validation.CustomerValidationStrategy;
import com.template.ms_customer.domain.model.BusinessCustomer;
import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.domain.record.CustomerValidationKey;
import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.stereotype.Component;

@Component
public class BusinessCustomerValidationStrategy implements CustomerValidationStrategy {
    @Override
    public void validate(Customer customer) {
        BusinessCustomer b = (BusinessCustomer) customer;

        if (b.getDocumentNumber() == null || !b.getDocumentNumber().matches(RegexConstants.PATTERN_RUC)) {
            throw new IllegalArgumentException(
                    MessageException.BUSINESS_INVALID_RUC.format(b.getDocumentNumber())
            );
        }

        if (b.getEmail() == null || !b.getEmail().matches(RegexConstants.PATTERN_EMAIL)) {
            throw new IllegalArgumentException(
                    MessageException.BUSINESS_INVALID_EMAIL.getMessage()
            );
        }

        if (b.getPhone() != null && !b.getPhone().matches(RegexConstants.PATTERN_PHONE)) {
            throw new IllegalArgumentException(
                    MessageException.BUSINESS_INVALID_PHONE.getMessage()
            );
        }

    }

    @Override
    public CustomerValidationKey supports() {
        return new CustomerValidationKey(
                CustomerType.BUSINESS,
                DocumentType.RUC
        );
    }
}
