package com.template.ms_customer.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class BusinessCustomer extends Customer {
    private String businessName;
    private String contactName;
}
