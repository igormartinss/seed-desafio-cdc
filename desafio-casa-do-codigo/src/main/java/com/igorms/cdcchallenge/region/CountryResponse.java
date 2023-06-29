package com.igorms.cdcchallenge.region;

import javax.validation.constraints.NotBlank;

public class CountryResponse {

    public CountryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @NotBlank
    public Long id;
    @NotBlank
    public String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CountryResponse fromEntity(Country country) {
        return new CountryResponse(
                country.getId(),
                country.getName()
        );
    }
}
