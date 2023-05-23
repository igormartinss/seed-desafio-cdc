package com.igorms.cdcchallenge.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController("/categories")
public class CategoryController {

    @PersistenceContext
    private final EntityManager entityManager;

    public CategoryController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody NewCategoryRequest request) {
        entityManager.persist(Category.fromRequest(request));
    }
}
