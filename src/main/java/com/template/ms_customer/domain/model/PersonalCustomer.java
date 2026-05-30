package com.template.ms_customer.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
public class PersonalCustomer extends Customer {
    private String names;
    private String lastName;
    private LocalDate birthDate;
}
