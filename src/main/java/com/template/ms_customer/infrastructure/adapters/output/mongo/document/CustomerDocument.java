package com.template.ms_customer.infrastructure.adapters.output.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.openapitools.model.CustomerProfile;
import org.openapitools.model.CustomerStatus;
import org.openapitools.model.CustomerType;
import org.openapitools.model.DocumentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document(collection = "customers")
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomerDocument {
    @Id
    private String id;

    private CustomerType customerType;
    private CustomerProfile profile;
    private DocumentType documentType;

    @Field("documentNumber")
    private String documentNumber;

    private String email;
    private String phone;
    private String address;
    private CustomerStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
