package com.igorms.cdcchallenge.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CategoryController {

    @PersistenceContext
    private final EntityManager entityManager;

    public CategoryController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping("/categories")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@Valid @RequestBody NewCategoryRequest request) {
        entityManager.persist(Category.fromRequest(request));
    }
}
