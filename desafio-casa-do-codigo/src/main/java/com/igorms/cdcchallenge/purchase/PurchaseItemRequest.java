package com.igorms.cdcchallenge.purchase;

import com.igorms.cdcchallenge.book.Book;
import com.igorms.cdcchallenge.shared.IdExists;

public class PurchaseItemRequest {

    @IdExists(domainClass = Book.class)
    private Long bookId;

    private Integer quantity;

    public Long getBookId() {
        return bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
