package com.igorms.cdcchallenge.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController("/authors")
public class AuthorController {

    @PersistenceContext
    private final EntityManager entityManager;
    @Autowired
    private DuplicatedEmailValidator duplicatedEmailValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(duplicatedEmailValidator);
    }

    public AuthorController(EntityManager manager) {
        super();
        this.entityManager = manager;
    }
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody NewAuthorRequest newAuthorRequest){
        entityManager.persist(Author.fromRequest(newAuthorRequest));
    }
}

