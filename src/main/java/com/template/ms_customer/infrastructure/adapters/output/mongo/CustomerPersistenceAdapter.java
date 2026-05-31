package com.template.ms_customer.infrastructure.adapters.output.mongo;

import com.template.ms_customer.domain.model.Customer;
import com.template.ms_customer.domain.port.CustomerPort;
import com.template.ms_customer.infrastructure.adapters.output.mongo.document.CustomerDocument;
import com.template.ms_customer.infrastructure.adapters.output.mapper.CustomerMapper;
import com.template.ms_customer.infrastructure.adapters.output.mapper.factory.CustomerMapperFactory;
import com.template.ms_customer.infrastructure.adapters.output.mongo.repository.CustomerMongoRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.CustomerResponse;
import org.openapitools.model.PagedCustomerResponse;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerPort {
    private final CustomerMongoRepository repository;
    private final CustomerMapperFactory factory;
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Customer> registerCustomer(Customer customer) {
        CustomerMapper mapper = factory.getMapper(customer.getCustomerType());
        customer.setCreatedAt(LocalDate.now());
        customer.setUpdatedAt(LocalDate.now());
        return repository.save(mapper.toDocument(customer))
                .map(mapper::toDomain);

    }

    @Override
    public Mono<CustomerResponse> findByDocumentNumber(String document) {
        return repository.findByDocumentNumber(document)
                .map(doc -> {
                    CustomerMapper mapper = factory.getMapper(doc.getCustomerType());
                    return mapper.toCustomerResponse(doc);
                });

    }

    @Override
    public Mono<PagedCustomerResponse> search(String status, String profile, String documentNumber, int page, int size) {

        Query query = new Query();

        if (status != null) {
            query.addCriteria(Criteria.where("status").is(status));
        }

        if (profile != null) {
            query.addCriteria(Criteria.where("profile").is(profile));
        }

        if (documentNumber != null) {
            query.addCriteria(Criteria.where("documentNumber").is(documentNumber));
        }

        Mono<Long> totalMono = mongoTemplate.count(query, CustomerDocument.class);

        Flux<CustomerResponse> contentFlux = mongoTemplate
                .find(query, CustomerDocument.class)
                .skip((long) page * size)
                .take(size)
                .map(doc -> {
                    CustomerMapper mapper = factory.getMapper(doc.getCustomerType());
                    return mapper.toCustomerResponse(doc);
                });

        Mono<List<CustomerResponse>> contentMono = contentFlux.collectList();

        return Mono.zip(contentMono, totalMono)
                .map(tuple -> {

                    List<CustomerResponse> content = tuple.getT1();
                    long totalElements = tuple.getT2();

                    int totalPages = (int) Math.ceil((double) totalElements / size);

                    return PagedCustomerResponse.builder()
                            .content(content)
                            .page(page)
                            .size(size)
                            .totalElements(Integer.parseInt(String.valueOf(totalElements)))
                            .totalPages(totalPages)
                            .build();
                });
    }

    @Override
    public Mono<Customer> findById(String id) {
        return repository.findById(id)
                .map(doc -> {
                    CustomerMapper mapper = factory.getMapper(doc.getCustomerType());
                    return mapper.toDomain(doc);
                });
    }

    @Override
    public Mono<Customer> updateCustomer(Customer customer) {
        CustomerMapper mapper = factory.getMapper(customer.getCustomerType());
        return repository.save(mapper.toDocument(customer))
                .map(mapper::toDomain);
    }
}
