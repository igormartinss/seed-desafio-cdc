package com.igorms.cdcchallenge.region;

import com.igorms.cdcchallenge.shared.UniqueValue;

public class NewStateRequest {

    @UniqueValue(domainClass = State.class, fieldName = "name")
    private String name;

    private Long countryId;

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }
}
