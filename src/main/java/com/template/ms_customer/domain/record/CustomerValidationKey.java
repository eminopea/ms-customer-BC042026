package com.template.ms_customer.domain.record;

import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;

public record CustomerValidationKey(
        CustomerType customerType,
        DocumentType documentType
) {}
