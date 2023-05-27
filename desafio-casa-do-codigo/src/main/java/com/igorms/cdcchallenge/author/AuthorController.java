package com.igorms.cdcchallenge.author;

import com.igorms.cdcchallenge.category.DuplicatedNameValidator;
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

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorResponse>> find(){
        Query query = entityManager.createQuery("select a from Author a");

        List<Author> authors = query.getResultList();
        List<AuthorResponse> authorsResponse = new ArrayList<>();

        authors.forEach(author -> authorsResponse.add(AuthorResponse.fromAuthor(author)));

        return new ResponseEntity<>(authorsResponse, HttpStatus.OK);
    }
}

