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
                newBookRequest.getIsbn(),
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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getMarkDownSummary() {
        return markDownSummary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public Category getCategory() {
        return category;
    }

    public Author getAuthor() {
        return author;
    }
}
