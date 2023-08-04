package com.igorms.cdcchallenge.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igorms.cdcchallenge.book.Book;
import com.igorms.cdcchallenge.shared.IdExists;

public class PurchaseItemRequest {

    @IdExists(domainClass = Book.class)
    @JsonProperty("book_id")
    private Long bookId;

    private Integer quantity;

    public Long getBookId() {
        return bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
