package com.samplecorp.config;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.samplecorp.domain.Customer;
import com.samplecorp.repository.CustomerRepository;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;

import io.reactivex.Single;

@Singleton
public class BootStrap {
    @Inject
    private CustomerRepository customerRepository;

    @EventListener
    public void onStartup(ServerStartupEvent event) {
        customerRepository.count()
            .flatMap(c -> {
                Single<Customer> sc = null;
                if (c == 0) {
                    Customer customer = null;
                    customer = Customer.builder()
                        .firstName("Ash")
                        .lastName("Ketchum")
                        .phone("+306912345678")
                        .email("ash@pallettown.net")
                        .build();
                    sc = customerRepository.save(customer);
                }
                return sc;
            })
            .subscribe();
    }
}
