package com.template.ms_customer.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.openapitools.model.CustomerProfile;
import org.openapitools.model.CustomerStatus;
import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@SuperBuilder
@Getter
@Setter
public class Customer {
    @Id
    private String id;

    private CustomerType customerType;
    private CustomerProfile profile;
    private DocumentType documentType;
    private String documentNumber;
    private String email;
    private String phone;
    private String address;
    private CustomerStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
