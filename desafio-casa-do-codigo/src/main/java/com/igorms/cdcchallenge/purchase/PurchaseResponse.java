package com.igorms.cdcchallenge.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igorms.cdcchallenge.purchase.cart.Cart;
import com.igorms.cdcchallenge.purchase.cart.CartResponse;
import com.igorms.cdcchallenge.purchase.coupon.Coupon;
import com.igorms.cdcchallenge.purchase.coupon.CouponResponse;
import com.igorms.cdcchallenge.region.Country;
import com.igorms.cdcchallenge.region.CountryResponse;
import com.igorms.cdcchallenge.region.State;
import com.igorms.cdcchallenge.region.StateResponse;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PurchaseResponse {

    public PurchaseResponse(String email, String name, String surname, String document, String address, String complement, CountryResponse country, StateResponse state, String phone, Long cep, CartResponse cart, CouponResponse coupon) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.cep = cep;
        this.cart = cart;
        this.coupon = coupon;
    }

    public PurchaseResponse(String email, String name, String surname, String document, String address, String complement, CountryResponse country, StateResponse state, String phone, Long cep, CartResponse cart) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.document = document;
        this.address = address;
        this.complement = complement;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.cep = cep;
        this.cart = cart;
    }

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @CPF
    @NotBlank
    private String document;

    @NotBlank
    private String address;

    @NotBlank
    private String complement;

    @NotNull
    private CountryResponse country;

    private StateResponse state;

    @NotBlank
    private String phone;

    @NotNull
    private Long cep;

    @NotNull
    private CartResponse cart;

    private CouponResponse coupon;

    public static PurchaseResponse fromEntity(Purchase purchase) {
        if (purchase.getCoupon() == null) {
            return new PurchaseResponse(
                    purchase.getEmail(),
                    purchase.getName(),
                    purchase.getSurname(),
                    purchase.getDocument(),
                    purchase.getAddress(),
                    purchase.getComplement(),
                    CountryResponse.fromEntity(purchase.getCountry()),
                    StateResponse.fromEntity(purchase.getState()),
                    purchase.getPhone(),
                    purchase.getCep(),
                    CartResponse.fromEntity(purchase.getCart())
            );
        }
        return new PurchaseResponse(
                purchase.getEmail(),
                purchase.getName(),
                purchase.getSurname(),
                purchase.getDocument(),
                purchase.getAddress(),
                purchase.getComplement(),
                CountryResponse.fromEntity(purchase.getCountry()),
                StateResponse.fromEntity(purchase.getState()),
                purchase.getPhone(),
                purchase.getCep(),
                CartResponse.fromEntity(purchase.getCart()),
                CouponResponse.fromEntity(purchase.getCoupon())
        );
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDocument() {
        return document;
    }

    public String getAddress() {
        return address;
    }

    public String getComplement() {
        return complement;
    }

    public CountryResponse getCountry() {
        return country;
    }

    public StateResponse getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public Long getCep() {
        return cep;
    }

    public CartResponse getCart() {
        return cart;
    }

    public CouponResponse getCoupon() {
        return coupon;
    }
}
