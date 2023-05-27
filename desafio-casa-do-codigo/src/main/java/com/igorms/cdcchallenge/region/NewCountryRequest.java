package com.igorms.cdcchallenge.region;

import com.igorms.cdcchallenge.shared.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NewCountryRequest {

    @NotBlank
    @UniqueValue(domainClass = Country.class, fieldName = "name")
    private String name;

    public String getName() {
        return this.name;
    }
}
