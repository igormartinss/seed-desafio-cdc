package com.igorms.cdcchallenge.category;


import com.igorms.cdcchallenge.shared.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "name", message = "Category already exists")
    private String name;

    @Deprecated
    public NewCategoryRequest() {
    }

    public String getName() {
        return name;
    }
}
