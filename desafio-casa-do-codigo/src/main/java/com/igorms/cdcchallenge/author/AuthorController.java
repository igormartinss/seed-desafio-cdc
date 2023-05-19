package com.igorms.cdcchallenge.author;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RestController
public class AuthorController {

    private EntityManager entityManager;
    @PostMapping
    @Transactional
    public void create(@RequestBody NewAuthorRequest newAuthorRequest){
        entityManager.persist(new Author(newAuthorRequest.name, newAuthorRequest.email, newAuthorRequest.description));
    }
}

