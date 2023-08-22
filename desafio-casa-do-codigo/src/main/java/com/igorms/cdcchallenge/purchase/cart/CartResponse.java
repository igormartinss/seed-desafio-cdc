package com.igorms.cdcchallenge.purchase.cart;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class CartResponse {

    public CartResponse(BigDecimal amount, BigDecimal amountWithDiscount, Set<CartItemResponse> cartItemResponse) {
        this.amount = amount;
        this.cartItemResponse = cartItemResponse;
        this.amountWithDiscount = amountWithDiscount;
    }

    private BigDecimal amount;

    private BigDecimal amountWithDiscount;

    private Set<CartItemResponse> cartItemResponse;

    public BigDecimal getAmount() {
        return amount;
    }
    public Set<CartItemResponse> getCartItemResponse() {
        return cartItemResponse;
    }

    public BigDecimal getAmountWithDiscount() {
        return amountWithDiscount;
    }

    public static CartResponse fromEntity(Cart cart) {
        Set<CartItemResponse> cartItemResponse = cart.getItems().stream().map(
                CartItemResponse::fromEntity).collect(Collectors.toSet());


        return new CartResponse(
                cart.getAmount(),
                cart.getAmountWithDiscount(),
                cartItemResponse
        );
    }
}
