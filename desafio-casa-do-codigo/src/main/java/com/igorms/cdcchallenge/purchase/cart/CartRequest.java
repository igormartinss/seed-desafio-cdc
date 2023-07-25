package com.igorms.cdcchallenge.purchase.cart;

import com.igorms.cdcchallenge.purchase.PurchaseItemRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class CartRequest {

    @Positive
    @NotNull
    private BigDecimal amount;

    @Size(min = 1)
    @Valid
    private List<PurchaseItemRequest> items;

    public BigDecimal getAmount() {
        return amount;
    }

    public List<PurchaseItemRequest> getItems() {
        return items;
    }
}
