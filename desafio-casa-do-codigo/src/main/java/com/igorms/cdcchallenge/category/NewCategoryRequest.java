package com.igorms.cdcchallenge.category;


import javax.validation.constraints.NotBlank;

public class NewCategoryRequest {

    @NotBlank
    private String name;

    @Deprecated
    public NewCategoryRequest() {
    }

    public String getName() {
        return name;
    }
}
