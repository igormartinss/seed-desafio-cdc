package com.igorms.cdcchallenge.purchase.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igorms.cdcchallenge.purchase.PurchaseItemRequest;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class CartRequest {

    @Positive
    @NotNull
    private BigDecimal amount;

    @Positive
    @Nullable
    @JsonProperty("amount_with_discount")
    private BigDecimal amountWithDiscount;

    @Size(min = 1)
    @Valid
    private List<PurchaseItemRequest> items;

    public BigDecimal getAmount() {
        return amount;
    }

    public List<PurchaseItemRequest> getItems() {
        return items;
    }

    public BigDecimal getAmountWithDiscount() {
        return amountWithDiscount;
    }
}
