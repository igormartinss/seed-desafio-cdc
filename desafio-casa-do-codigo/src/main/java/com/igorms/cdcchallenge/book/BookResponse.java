package com.igorms.cdcchallenge.book;

import com.igorms.cdcchallenge.author.Author;
import com.igorms.cdcchallenge.author.AuthorResponse;
import com.igorms.cdcchallenge.category.Category;
import com.igorms.cdcchallenge.category.CategoryResponse;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BookResponse {

    private Long id;

    private String title;

    private String summary;

    private String markDownSummary;

    private BigDecimal price;

    private Integer numberOfPages;

    private String isbn;

    private LocalDate publishedDate;

    @ManyToOne
    private CategoryResponse categoryResponse;

    @ManyToOne
    private AuthorResponse authorResponse;

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

    public CategoryResponse getCategoryResponse() {
        return categoryResponse;
    }

    public AuthorResponse getAuthorResponse() {
        return authorResponse;
    }

    public static BookResponse fromBook(Book book){
        BookResponse bookResponse = new BookResponse();
        bookResponse.id = book.getId();
        bookResponse.title = book.getTitle();
        bookResponse.summary = book.getSummary();
        bookResponse.markDownSummary = book.getMarkDownSummary();
        bookResponse.price = book.getPrice();
        bookResponse.numberOfPages = book.getNumberOfPages();
        bookResponse.isbn = book.getIsbn();
        bookResponse.publishedDate = book.getPublishedDate();
        bookResponse.categoryResponse = CategoryResponse.fromCategory(book.getCategory());
        bookResponse.authorResponse = AuthorResponse.fromAuthor(book.getAuthor());
        return bookResponse;
    }

}
