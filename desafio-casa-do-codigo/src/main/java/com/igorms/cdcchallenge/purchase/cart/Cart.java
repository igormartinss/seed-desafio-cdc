package com.igorms.cdcchallenge.purchase.cart;

import com.igorms.cdcchallenge.purchase.Purchase;
import com.igorms.cdcchallenge.purchase.coupon.Coupon;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Cart {

    public Cart(BigDecimal amount, BigDecimal amountWithDiscount, Purchase purchase, Set<CartItem> items) {
        this.amount = amount;
        this.amount = amountWithDiscount;
        this.purchase = purchase;
        this.items = items;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;

    private BigDecimal amountWithDiscount;

    @OneToOne
    private Purchase purchase;

    @ElementCollection
    private @Size(min = 1) @Valid Set<CartItem> items = new HashSet<>();

    @Deprecated
    public Cart() {

    }

    public static Function<Purchase, Cart> fromRequest(CartRequest cartRequest, Coupon coupon, EntityManager entityManager) {
        Set<CartItem> items = cartRequest.getItems().stream().map(
                item -> CartItem.fromRequest(item, entityManager)).collect(Collectors.toSet());

        Assert.state(isAmountValid(cartRequest.getAmount(), items), "Valor total de compra inválido!");

        if (coupon != null) {
            Assert.state(
                    isAmountWithDiscountValid(cartRequest.getAmountWithDiscount(), cartRequest.getAmount(), coupon.getPercentage()),
                    "Valor com desconto inválido!"
            );
        }

        return (purchase) -> new Cart(cartRequest.getAmount(), cartRequest.getAmountWithDiscount(), purchase, items);
    }

    private static Boolean isAmountValid(BigDecimal amountReceived, Set<CartItem> items) {
        BigDecimal actualAmount = BigDecimal.ZERO;

        for (CartItem cartItem : items) {
            BigDecimal itemAmount = cartItem.getBook().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            actualAmount = actualAmount.add(itemAmount);
        }

        return Objects.equals(amountReceived, actualAmount);
    }

    private static Boolean isAmountWithDiscountValid(
            BigDecimal amountWithDiscountReceived,
            BigDecimal amountReceived,
            BigDecimal discountReceived
    ) {
        BigDecimal percentage = amountReceived.divide(BigDecimal.valueOf(100));
        BigDecimal amountWithDiscount = amountReceived.subtract(percentage.multiply(discountReceived)).setScale(2);

        return amountWithDiscount.equals(amountWithDiscountReceived);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getAmountWithDiscount() {
        return amountWithDiscount;
    }

    public Set<CartItem> getItems() {
        return items;
    }
}
