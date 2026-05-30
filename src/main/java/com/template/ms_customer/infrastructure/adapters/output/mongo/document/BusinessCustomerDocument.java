package com.template.ms_customer.infrastructure.adapters.output.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessCustomerDocument extends CustomerDocument{
    private String businessName;
    private String contactName;
}
