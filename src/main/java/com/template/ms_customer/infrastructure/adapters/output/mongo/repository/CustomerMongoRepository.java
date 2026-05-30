package com.template.ms_customer.infrastructure.adapters.output.mongo.repository;

import com.template.ms_customer.infrastructure.adapters.output.mongo.document.CustomerDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerMongoRepository extends ReactiveMongoRepository<CustomerDocument, String> {
    Mono<CustomerDocument> findByDocumentNumber(String documentNumber);
}
