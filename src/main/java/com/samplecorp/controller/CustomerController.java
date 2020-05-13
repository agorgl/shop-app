package com.samplecorp.controller;

import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;

import com.samplecorp.domain.Customer;
import com.samplecorp.repository.CustomerRepository;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Status;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Controller("/customers")
public class CustomerController {
    @Inject
    private CustomerRepository customerRepository;

    @Get
    @Status(HttpStatus.OK)
    public Single<List<Customer>> findAll() {
        Flowable<Customer> f = customerRepository.findAll();
        return f.toList();
    }

    @Get("/{id}")
    @Status(HttpStatus.OK)
    public Maybe<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Post
    @Status(HttpStatus.CREATED)
    public Single<Customer> create(@Valid @Body Customer c) {
        return customerRepository.save(c);
    }

    @Put("/{id}")
    @Status(HttpStatus.OK)
    public Single<Customer> update(Long id, @Body Customer c) {
        return customerRepository.save(c);
    }

    @Delete("/{id}")
    @Status(HttpStatus.NO_CONTENT)
    public Maybe<Boolean> delete(Long id) {
        return customerRepository.existsById(id)
            .flatMapMaybe(x -> {
                Maybe<Boolean> m = customerRepository.deleteById(id).toMaybe();
                return x
                    ? m.switchIfEmpty(Maybe.just(true))
                    : Maybe.empty();
            });
    }
}
