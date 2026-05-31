package com.template.ms_customer.infrastructure.adapters.output.mapper;

import com.template.ms_customer.domain.model.BusinessCustomer;
import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.infrastructure.adapters.output.mongo.document.BusinessCustomerDocument;
import com.template.ms_customer.infrastructure.adapters.output.mongo.document.CustomerDocument;
import org.openapitools.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BusinessCustomerMapper implements CustomerMapper {

    @Override
    public Customer toDomain(CustomerDocument document) {
        BusinessCustomerDocument doc = (BusinessCustomerDocument) document;

        return BusinessCustomer.builder()
                .id(doc.getId())
                .customerType(doc.getCustomerType())
                .profile(doc.getProfile())
                .documentType(doc.getDocumentType())
                .documentNumber(doc.getDocumentNumber())
                .email(doc.getEmail())
                .phone(doc.getPhone())
                .address(doc.getAddress())
                .status(doc.getStatus())
                .businessName(doc.getBusinessName())
                .contactName(doc.getContactName())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }

    @Override
    public CustomerDocument toDocument(Customer customer) {
        BusinessCustomer b = (BusinessCustomer) customer;

        return BusinessCustomerDocument.builder()
                .id(b.getId())
                .customerType(b.getCustomerType())
                .profile(b.getProfile())
                .documentType(b.getDocumentType())
                .documentNumber(b.getDocumentNumber())
                .email(b.getEmail())
                .phone(b.getPhone())
                .address(b.getAddress())
                .status(b.getStatus())
                .businessName(b.getBusinessName())
                .contactName(b.getContactName())
                .createdAt(b.getCreatedAt())
                .updatedAt(b.getUpdatedAt())
                .build();
    }

    @Override
    public Customer fromGenerate(BaseCustomerRequest request) {
        BusinessCustomerRequest doc = (BusinessCustomerRequest) request;
        return BusinessCustomer.builder()
                .customerType(doc.getCustomerType())
                .profile(doc.getProfile())
                .documentType(doc.getDocumentType())
                .documentNumber(doc.getDocumentNumber())
                .email(doc.getEmail())
                .phone(doc.getPhone())
                .address(doc.getAddress())
                .status(doc.getStatus())
                .businessName(doc.getBusinessName())
                .contactName(doc.getContactName())
                .createdAt(LocalDate.now())
                .build();
    }

    @Override
    public CustomerResponse toCustomerResponse(CustomerDocument request) {
        BusinessCustomerDocument doc = (BusinessCustomerDocument) request;
        return BusinessCustomerResponse.builder()
                .customerId(doc.getId())
                .customerType(doc.getCustomerType())
                .profile(doc.getProfile())
                .documentType(doc.getDocumentType())
                .documentNumber(doc.getDocumentNumber())
                .email(doc.getEmail())
                .phone(doc.getPhone())
                .address(doc.getAddress())
                .status(doc.getStatus())
                .businessName(doc.getBusinessName())
                .contactName(doc.getContactName())
                .createdAt(doc.getCreatedAt())
                .updatedAt(doc.getUpdatedAt())
                .build();
    }

    @Override
    public Customer fromGenerateUpdate(BaseCustomerRequest request) {
        BusinessCustomerRequest doc = (BusinessCustomerRequest) request;
        return BusinessCustomer.builder()
                .customerType(doc.getCustomerType())
                .profile(doc.getProfile())
                .documentType(doc.getDocumentType())
                .documentNumber(doc.getDocumentNumber())
                .email(doc.getEmail())
                .phone(doc.getPhone())
                .address(doc.getAddress())
                .status(doc.getStatus())
                .businessName(doc.getBusinessName())
                .contactName(doc.getContactName())
                .createdAt(doc.getCreatedAt())
                .updatedAt(LocalDate.now())
                .build();
    }

    @Override
    public CustomerType support() {
        return CustomerType.BUSINESS;
    }

}
