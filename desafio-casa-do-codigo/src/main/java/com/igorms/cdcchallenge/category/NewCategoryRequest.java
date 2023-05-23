package com.igorms.cdcchallenge.category;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
