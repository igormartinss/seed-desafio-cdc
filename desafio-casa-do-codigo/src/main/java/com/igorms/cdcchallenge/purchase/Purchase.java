package com.igorms.cdcchallenge.purchase;

import com.igorms.cdcchallenge.region.Country;
import com.igorms.cdcchallenge.region.State;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Purchase {

    public Purchase(String email, String name, String surname, String document, String address,
                    String complement, Country country, State state, String phone, Long cep) {
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
    @CNPJ
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

    @Deprecated
    public Purchase() {

    }

    public static Purchase fromRequest(PurchaseRequest purchaseRequest, EntityManager entityManager) {
        Country country = entityManager.find(Country.class, purchaseRequest.getCountryId());
        State state = entityManager.find(State.class, purchaseRequest.getStateId());

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
                purchaseRequest.getCep()
        );
    }
}
