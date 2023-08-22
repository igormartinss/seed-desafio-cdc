package com.igorms.cdcchallenge.purchase.cart;

import com.igorms.cdcchallenge.book.Book;

public class CartItemResponse {

    public CartItemResponse(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    private Book book;

    private Integer quantity;

    public Book getBook() {
        return book;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static CartItemResponse fromEntity(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getBook(),
                cartItem.getQuantity()
        );
    }
}
