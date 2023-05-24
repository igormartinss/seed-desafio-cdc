package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.category.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/books")
    @Transactional
    public void createBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        entityManager.persist(Book.fromRequest(newBookRequest, entityManager));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> find(){
        Query query = entityManager.createQuery("select b from Book b");
        List<Book> books = query.getResultList();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
