package com.igorms.cdcchallenge.book;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.igorms.cdcchallenge.shared.UniqueValue;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequest {

    public NewBookRequest(String title, String summary, String markDownSummary, BigDecimal price, Integer numberOfPages, String isbn, LocalDate publishedDate, String categoryName, String authorName) {
        this.title = title;
        this.summary = summary;
        this.markDownSummary = markDownSummary;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.categoryName = categoryName;
        this.authorName = authorName;
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
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate publishedDate;

    @NotBlank
    private String categoryName;

    @NotBlank
    private String authorName;

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

    public String getLsbn() {
        return isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public NewBookRequest() {
    }


    public String getAuthorName() {
        return authorName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
