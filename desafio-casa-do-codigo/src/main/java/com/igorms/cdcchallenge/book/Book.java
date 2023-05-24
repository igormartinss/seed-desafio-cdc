package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.category.Category;

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

    public static Book fromRequest(NewBookRequest newBookRequest, Category category, Author author) {
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

}
