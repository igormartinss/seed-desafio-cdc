package com.igorms.cdcchallenge.book;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.igorms.cdcchallenge.shared.UniqueValue;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequest {

    public NewBookRequest(String title, String summary, String markDownSummary, BigDecimal price, Integer numberOfPages, String isbn, LocalDate publishedDate, Long categoryId, Long authorId) {
        this.title = title;
        this.summary = summary;
        this.markDownSummary = markDownSummary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.categoryId = categoryId;
        this.authorId = authorId;
    }

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "title")
    private String title;

    @NotBlank
    @Size(max = 500)
    private String summary;

    private String markDownSummary;

    @Min(value = 20)
    private BigDecimal price;

    @Min(value = 20)
    private Integer numberOfPages;

    @NotBlank
    @UniqueValue(domainClass = Book.class, fieldName = "isbn")
    private String isbn;

    @Future
    private LocalDate publishedDate;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long authorId;

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

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
