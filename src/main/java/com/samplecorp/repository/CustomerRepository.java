package com.samplecorp.repository;

import com.samplecorp.domain.Customer;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.reactive.RxJavaCrudRepository;

@Repository
public interface CustomerRepository extends RxJavaCrudRepository<Customer, Long> {}
