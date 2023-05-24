package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.category.Category;
import org.springframework.util.Assert;
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
        Category categoryResult = getRequestCategory(newBookRequest.getCategoryName());

        Author authorResult = getAuthorCategory(newBookRequest.getAuthorName());

        entityManager.persist(Book.fromRequest(newBookRequest, categoryResult, authorResult));
    }

    private Category getRequestCategory(String categoryName) {
        Query categoryQuery = entityManager.createQuery("select c from Category c where name = :value");
        categoryQuery.setParameter("value", categoryName);
        Object categoryFound = categoryQuery.getSingleResult();
        Assert.state(categoryFound != null, "No category found for this name: " + categoryName);
        return (Category) categoryFound;
    }

    private Author getAuthorCategory(String authorName) {
        Query authorQuery = entityManager.createQuery("select a from Author a where name =:value");
        authorQuery.setParameter("value", authorName);
        Object authorFound = authorQuery.getSingleResult();
        Assert.state(authorFound != null, "No author found for this name: " + authorName);
        return (Author) authorFound;
    }

}
