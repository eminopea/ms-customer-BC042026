package com.template.ms_customer.application.validation.impl;

import com.template.ms_customer.application.shared.MessageException;
import com.template.ms_customer.application.shared.RegexConstants;
import com.template.ms_customer.application.validation.CustomerValidationStrategy;
import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.domain.model.PersonalCustomer;
import com.template.ms_customer.domain.record.CustomerValidationKey;
import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonalCustomerValidationStrategy implements CustomerValidationStrategy {
    @Override
    public void validate(Customer customer) {
        PersonalCustomer p = (PersonalCustomer) customer;

        if (p.getDocumentNumber() == null || !p.getDocumentNumber().matches(RegexConstants.PATTERN_DNI))
            throw new IllegalArgumentException(
                    MessageException.PERSONAL_INVALID_DNI.format(customer.getDocumentNumber())
            );

        if (p.getEmail() == null || !p.getEmail().matches(RegexConstants.PATTERN_EMAIL))
            throw new IllegalArgumentException(
                    MessageException.PERSONAL_INVALID_EMAIL.format(customer.getEmail())
            );

        if (p.getPhone() == null || !p.getPhone().matches(RegexConstants.PATTERN_PHONE))  
            throw new IllegalArgumentException(
                    MessageException.PERSONAL_INVALID_PHONE.format(customer.getPhone())
            );

        if (p.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException(
                    MessageException.PERSONAL_UNDERAGE.getMessage()
            );
        }


    }

    @Override
    public CustomerValidationKey supports() {
        return new CustomerValidationKey(
                CustomerType.PERSONAL,
                DocumentType.DNI
        );
    }
}
