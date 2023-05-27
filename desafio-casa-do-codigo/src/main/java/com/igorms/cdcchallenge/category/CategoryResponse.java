package com.igorms.cdcchallenge.category;

import javax.validation.constraints.NotBlank;

public class CategoryResponse {

    @NotBlank
    private Long id;

    @NotBlank
    private String name;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static CategoryResponse fromCategory(Category category){
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.id = category.getId();
        categoryResponse.name = category.getName();
        return categoryResponse;
    }

}
