package com.igorms.cdcchallenge.purchase.cart;

import com.igorms.cdcchallenge.purchase.Purchase;
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

    public Cart(BigDecimal amount, Purchase purchase, Set<CartItem> items) {
        this.amount = amount;
        this.purchase = purchase;
        this.items = items;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal amount;

    @OneToOne
    private Purchase purchase;

    @ElementCollection
    private @Size(min = 1) @Valid Set<CartItem> items = new HashSet<>();

    @Deprecated
    public Cart() {

    }

    public static Function<Purchase, Cart> fromRequest(CartRequest cartRequest, EntityManager entityManager) {
        Set<CartItem> items = cartRequest.getItems().stream().map(
                item -> CartItem.fromRequest(item, entityManager)).collect(Collectors.toSet());

        Assert.state(isAmountValid(cartRequest.getAmount(), items), "Valor total de compra inválido!");

        return (purchase) -> new Cart(cartRequest.getAmount(), purchase, items);
    }

    private static Boolean isAmountValid(BigDecimal amountReceived, Set<CartItem> items) {
        BigDecimal actualAmount = BigDecimal.ZERO;

        for (CartItem cartItem : items) {
            BigDecimal itemAmount = cartItem.getBook().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            actualAmount = actualAmount.add(itemAmount);
        }

        return Objects.equals(amountReceived, actualAmount);
    }
}
