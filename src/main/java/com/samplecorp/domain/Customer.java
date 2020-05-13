package com.samplecorp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.micronaut.core.annotation.Introspected;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Introspected
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "\\+(.+)[0-9]+", message = "Invalid phone")
    private String phone;

    @Pattern(regexp = "\\S+@\\S+\\.\\S+", message = "Invalid email")
    private String email;
}
