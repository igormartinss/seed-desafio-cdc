package com.igorms.cdcchallenge.category;

import com.igorms.cdcchallenge.author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> find(){
        Query query = entityManager.createQuery("select c from Category c");

        List<Category> categories = query.getResultList();
        List<CategoryResponse> categoriesResponse = new ArrayList<>();

        categories.forEach(category -> categoriesResponse.add(CategoryResponse.fromCategory(category)));

        return new ResponseEntity<>(categoriesResponse, HttpStatus.OK);
    }


}
