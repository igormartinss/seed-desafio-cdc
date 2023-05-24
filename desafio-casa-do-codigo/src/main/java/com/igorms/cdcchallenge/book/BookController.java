package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.category.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/books")
    public void createBook(@RequestBody @Valid NewBookRequest newBookRequest) {
        Category categoryResult = getRequestCategory(newBookRequest.getCategoryName());

        Author authorResult = getAuthorCategory(newBookRequest.getAuthorName());

        entityManager.persist(Book.fromRequest(newBookRequest, categoryResult, authorResult));
    }

    private Category getRequestCategory(String categoryName) {
        Query categoryQuery = entityManager.createQuery("select 1 from Category where name =:value");
        categoryQuery.setParameter("value", categoryName);
        Category category = (Category) categoryQuery.getResultList().get(0);
        return category;
    }

    private Author getAuthorCategory(String authorName) {
        Query authorQuery = entityManager.createQuery("select 1 from Author where name =:value");
        authorQuery.setParameter("value", authorName);
        return (Author) authorQuery.getSingleResult();
    }

}
