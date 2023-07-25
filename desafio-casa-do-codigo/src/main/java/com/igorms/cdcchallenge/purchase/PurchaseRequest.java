package com.igorms.cdcchallenge.purchase;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.igorms.cdcchallenge.purchase.cart.CartRequest;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PurchaseRequest {
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
    @Valid
    private CartRequest cartRequest;


    @NotNull
    @JsonProperty("country_id")
    private Long countryId;

    @NotNull
    @JsonProperty("state_id")
    private Long stateId;

    @NotBlank
    private String phone;

    @NotNull
    private Long cep;

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

    public Long getCountryId() {
        return countryId;
    }

    public Long getStateId() {
        return stateId;
    }

    public String getPhone() {
        return phone;
    }

    public Long getCep() {
        return cep;
    }

    public CartRequest getCartRequest() {
        return cartRequest;
    }
}
