package com.igorms.cdcchallenge.region;

import com.igorms.cdcchallenge.shared.UniqueValue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Country {

    public Country(String name) {
        this.name = name;
    }

    @Deprecated
    public Country() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotBlank
    private String name;

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public static Country fromRequest(NewCountryRequest newCountryRequest) {
        return new Country(newCountryRequest.getName());
    }
}
