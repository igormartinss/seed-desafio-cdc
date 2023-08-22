package com.igorms.cdcchallenge.purchase;

import com.igorms.cdcchallenge.purchase.cart.Cart;
import com.igorms.cdcchallenge.purchase.cart.CartRequest;
import com.igorms.cdcchallenge.purchase.coupon.Coupon;
import com.igorms.cdcchallenge.region.Country;
import com.igorms.cdcchallenge.region.State;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

@Entity
public class Purchase {

    public Purchase(String email, String name, String surname, String document, String address,
                    String complement, Country country, State state, String phone, Long cep,
                    CartRequest cartRequest, Coupon coupon, EntityManager entityManager) {

        Function<Purchase, Cart> cartFunction = Cart.fromRequest(cartRequest, coupon, entityManager);
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
        this.coupon = coupon;
        this.cart = cartFunction.apply(this);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @NotNull
    private Country country;

    @ManyToOne
    private State state;

    @NotBlank
    private String phone;

    @NotNull
    private Long cep;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;

    @Nullable
    @ManyToOne
    private Coupon coupon;

    @Nullable
    private BigDecimal discountPrice;

    @Deprecated
    public Purchase() {}

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

    public Country getCountry() {
        return country;
    }

    public State getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public Long getCep() {
        return cep;
    }

    public Cart getCart() {
        return cart;
    }

    @Nullable
    public Coupon getCoupon() {
        return coupon;
    }

    public static Purchase fromRequest(PurchaseRequest purchaseRequest, EntityManager entityManager) {
        Country country = entityManager.find(Country.class, purchaseRequest.getCountryId());
        State state = entityManager.find(State.class, purchaseRequest.getStateId());

        Query query = entityManager.createQuery("select c from Coupon c where c.coupon = '" + purchaseRequest.getCoupon() + "'");
        Optional<Coupon> coupon = query.getResultList().stream().findFirst();

        Assert.state(country != null, "No country found for this ID: " + purchaseRequest.getCountryId());
        Assert.state(state != null, "No state found for this ID: " + purchaseRequest.getStateId());

        return new Purchase(
                purchaseRequest.getEmail(),
                purchaseRequest.getName(),
                purchaseRequest.getSurname(),
                purchaseRequest.getDocument(),
                purchaseRequest.getAddress(),
                purchaseRequest.getComplement(),
                country,
                state,
                purchaseRequest.getPhone(),
                purchaseRequest.getCep(),
                purchaseRequest.getCart(),
                coupon.orElse(null),
                entityManager
        );
    }

}
