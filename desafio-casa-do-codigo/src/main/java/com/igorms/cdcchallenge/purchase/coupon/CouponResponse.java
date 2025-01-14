package com.igorms.cdcchallenge.purchase.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CouponResponse {

    public CouponResponse(String coupon, LocalDate expireAt, BigDecimal percentage) {
        this.coupon = coupon;
        this.expireAt = expireAt;
        this.percentage = percentage;
    }

    private String coupon;

    @JsonProperty("expire_at")
    private LocalDate expireAt;

    @JsonProperty("usage_limit")
    private Integer usageLimit;

    private BigDecimal percentage;

    public String getCoupon() {
        return coupon;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }


    public BigDecimal getPercentage() {
        return percentage;
    }

    public static CouponResponse fromEntity(Coupon coupon) {
        return new CouponResponse(
                coupon.getCoupon(),
                coupon.getExpireAt(),
                coupon.getPercentage()
        );
    }
}
