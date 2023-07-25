package com.igorms.cdcchallenge.purchase.cart;

import com.igorms.cdcchallenge.book.Book;
import com.igorms.cdcchallenge.purchase.PurchaseItemRequest;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;

@Embeddable
public class CartItem {

    public CartItem(Book book, Integer quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    @ManyToOne
    private Book book;

    private Integer quantity;

    @Deprecated
    public CartItem() {

    }

    public static CartItem fromRequest(PurchaseItemRequest item, EntityManager entityManager) {
        Book book = entityManager.find(Book.class, item.getBookId());
        Assert.isTrue(book != null, "Livro não existe");
        return new CartItem(book, item.getQuantity());
    }
}
