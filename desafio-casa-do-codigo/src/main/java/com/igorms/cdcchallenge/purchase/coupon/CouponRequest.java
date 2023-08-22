package com.igorms.cdcchallenge.purchase.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igorms.cdcchallenge.shared.IdExists;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponRequest {

    @NotBlank
    private String coupon;

    @NotNull
    @Positive
    private BigDecimal percentage;

    @NotNull
    @JsonProperty("expire_at")
    @Future
    private LocalDate expireAt;


    public String getCoupon() {
        return coupon;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

}
