package com.template.ms_customer.infrastructure.adapters.output.mongo.mapper;

import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.domain.model.PersonalCustomer;
import com.template.ms_customer.infrastructure.adapters.output.mongo.document.CustomerDocument;
import com.template.ms_customer.infrastructure.adapters.output.mongo.document.PersonalCustomerDocument;
import org.openapitools.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonalCustomerMapper implements CustomerMapper{
    @Override
    public Customer toDomain(CustomerDocument document) {
        PersonalCustomerDocument object = (PersonalCustomerDocument) document;
        return PersonalCustomer.builder()
                .id(object.getId())
                .customerType(object.getCustomerType())
                .profile(object.getProfile())
                .documentType(object.getDocumentType())
                .documentNumber(object.getDocumentNumber())
                .email(object.getEmail())
                .phone(object.getPhone())
                .address(object.getAddress())
                .status(object.getStatus())
                .names(object.getNames())
                .lastName(object.getLastName())
                .birthDate(object.getBirthDate())
                .createdAt(object.getCreatedAt())
                .updatedAt(object.getUpdatedAt())
                .build();
    }

    @Override
    public CustomerDocument toDocument(Customer customer) {
        PersonalCustomer p = (PersonalCustomer) customer;
        return PersonalCustomerDocument.builder()
                .id(p.getId())
                .customerType(p.getCustomerType())
                .profile(p.getProfile())
                .documentType(p.getDocumentType())
                .documentNumber(p.getDocumentNumber())
                .email(p.getEmail())
                .phone(p.getPhone())
                .address(p.getAddress())
                .status(p.getStatus())
                .names(p.getNames())
                .lastName(p.getLastName())
                .birthDate(p.getBirthDate())
                .createdAt(p.getCreatedAt())
                .updatedAt(p.getUpdatedAt())
                .build();

    }

    @Override
    public Customer fromGenerate(BaseCustomerRequest request) {
        PersonalCustomerRequest doc = (PersonalCustomerRequest) request;
        return PersonalCustomer.builder()
                .customerType(doc.getCustomerType())
                .profile(doc.getProfile())
                .documentType(doc.getDocumentType())
                .documentNumber(doc.getDocumentNumber())
                .email(doc.getEmail())
                .phone(doc.getPhone())
                .address(doc.getAddress())
                .status(doc.getStatus())
                .names(doc.getNames())
                .lastName(doc.getLastName())
                .birthDate(doc.getBirthDate())
                .createdAt(LocalDate.now())
                .build();
    }

    @Override
    public CustomerResponse toCustomerResponse(CustomerDocument request) {
        PersonalCustomerDocument object = (PersonalCustomerDocument) request;
        return PersonalCustomerResponse.builder()
                .customerId(object.getId())
                .customerType(object.getCustomerType())
                .profile(object.getProfile())
                .documentType(object.getDocumentType())
                .documentNumber(object.getDocumentNumber())
                .email(object.getEmail())
                .phone(object.getPhone())
                .address(object.getAddress())
                .status(object.getStatus())
                .names(object.getNames())
                .lastName(object.getLastName())
                .birthDate(object.getBirthDate())
                .createdAt(object.getCreatedAt())
                .updatedAt(object.getUpdatedAt())
                .build();
    }

    @Override
    public Customer fromGenerateUpdate(BaseCustomerRequest request) {
        PersonalCustomerRequest doc = (PersonalCustomerRequest) request;
        return PersonalCustomer.builder()
                .customerType(doc.getCustomerType())
                .profile(doc.getProfile())
                .documentType(doc.getDocumentType())
                .documentNumber(doc.getDocumentNumber())
                .email(doc.getEmail())
                .phone(doc.getPhone())
                .address(doc.getAddress())
                .status(doc.getStatus())
                .names(doc.getNames())
                .lastName(doc.getLastName())
                .birthDate(doc.getBirthDate())
                .createdAt(doc.getCreatedAt())
                .updatedAt(LocalDate.now())
                .build();
    }

    @Override
    public CustomerType support() {
        return CustomerType.PERSONAL;
    }
}
