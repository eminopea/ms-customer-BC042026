package com.template.ms_customer.infrastructure.adapters.output.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalCustomerDocument extends CustomerDocument{
    private String names;
    private String lastName;
    private LocalDate birthDate;
}
