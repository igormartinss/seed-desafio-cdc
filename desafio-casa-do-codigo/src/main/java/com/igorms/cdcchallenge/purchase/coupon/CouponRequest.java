package com.igorms.cdcchallenge.purchase.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponRequest {

    @NotBlank
    private String coupon;

    @NotNull
    private BigDecimal percentage;

    @Nullable
    @JsonProperty("usage_limit")
    private Integer usageLimit;

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

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

}
