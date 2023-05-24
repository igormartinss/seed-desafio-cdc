package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.category.Category;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Book {

    @Deprecated
    public Book() {
    }

    public Book(String title, String summary, String markDownSummary, BigDecimal price, Integer numberOfPages, String isbn, LocalDate publishedDate, Category category, Author author) {
        this.title = title;
        this.summary = summary;
        this.markDownSummary = markDownSummary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.category = category;
        this.author = author;
    }

    public static Book fromRequest(NewBookRequest newBookRequest, EntityManager entityManager) {
        Category category = entityManager.find(Category.class, newBookRequest.getCategoryId());
        Author author = entityManager.find(Author.class, newBookRequest.getAuthorId());

        Assert.state(category != null, "No category found for this ID: " + newBookRequest.getCategoryId());
        Assert.state(author != null, "No category found for this ID: " + newBookRequest.getAuthorId());

        return new Book(
                newBookRequest.getTitle(),
                newBookRequest.getSummary(),
                newBookRequest.getMarkDownSummary(),
                newBookRequest.getPrice(),
                newBookRequest.getNumberOfPages(),
                newBookRequest.getLsbn(),
                newBookRequest.getPublishedDate(),
                category,
                author
        );
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private String markDownSummary;

    private BigDecimal price;

    private Integer numberOfPages;

    private String isbn;

    private LocalDate publishedDate;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Author author;

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