package com.igorms.cdcchallenge.category;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Deprecated
    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public static Category fromRequest(NewCategoryRequest request) {
        return new Category(request.getName());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
