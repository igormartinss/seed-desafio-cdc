package com.igorms.cdcchallenge.author;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class AuthorController {

    private final EntityManager entityManager;

    public AuthorController(EntityManager manager) {
        super();
        this.entityManager = manager;
    }
    @PostMapping("/author")
    @Transactional
    public void create(@Valid @RequestBody NewAuthorRequest newAuthorRequest){
        entityManager.persist(Author.fromRequest(newAuthorRequest));
    }
}

