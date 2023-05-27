package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.category.Category;
import com.igorms.cdcchallenge.category.CategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<BookResponse>> find(){
        Query query = entityManager.createQuery("select b from Book b");

        List<Book> books = query.getResultList();
        List<BookResponse> booksResponse = new ArrayList<>();
        books.forEach(book -> booksResponse.add(BookResponse.fromBook(book)));

        return new ResponseEntity<>(booksResponse, HttpStatus.OK);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> findById(@PathVariable("id") Long id){

        Book book = Optional.ofNullable(entityManager.find(Book.class, id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        BookResponse bookResponse = BookResponse.fromBook(book);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);
    }

}
