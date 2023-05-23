package com.igorms.cdcchallenge.author;

import com.igorms.cdcchallenge.category.DuplicatedNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AuthorController {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorController(EntityManager manager) {
        super();
        this.entityManager = manager;
    }
    @PostMapping("/authors")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody NewAuthorRequest newAuthorRequest){
        entityManager.persist(Author.fromRequest(newAuthorRequest));
    }
}

