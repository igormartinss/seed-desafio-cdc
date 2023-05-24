package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.category.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/books")
    @Transactional
    public void createBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        entityManager.persist(Book.fromRequest(newBookRequest, entityManager));
    }

}
