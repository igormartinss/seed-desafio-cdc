package com.igorms.cdcchallenge.book;


import com.igorms.cdcchallenge.shared.UniqueValue;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewBookRequest {

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
    @UniqueValue(domainClass = Book.class, fieldName = "lsbn")
    private String lsbn;

    @FutureOrPresent
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
        return lsbn;
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
