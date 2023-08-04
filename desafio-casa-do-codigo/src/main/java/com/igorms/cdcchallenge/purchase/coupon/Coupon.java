package com.igorms.cdcchallenge.purchase.coupon;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Coupon {

    public Coupon(String coupon, BigDecimal percentage, @Nullable Integer usageLimit, LocalDate expireAt) {
        this.coupon = coupon;
        this.percentage = percentage;
        this.usageLimit = usageLimit;
        this.expireAt = expireAt;
        this.createdAt = LocalDate.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String coupon;

    @NotNull
    private BigDecimal percentage;

    @Nullable
    private Integer usageLimit;

    @NotNull
    private LocalDate expireAt;

    @NotNull
    private LocalDate createdAt;

    @Deprecated
    public Coupon() {}

    public String getCoupon() {
        return coupon;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    @Nullable
    public Integer getUsageLimit() {
        return usageLimit;
    }

    public LocalDate getExpireAt() {
        return expireAt;
    }

    public static Coupon fromRequest(CouponRequest couponRequest) {
        return new Coupon(
                couponRequest.getCoupon(),
                couponRequest.getPercentage(),
                couponRequest.getUsageLimit(),
                couponRequest.getExpireAt()
        );
    }
}
