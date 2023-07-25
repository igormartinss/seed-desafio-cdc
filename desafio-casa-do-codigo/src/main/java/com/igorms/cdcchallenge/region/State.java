package com.igorms.cdcchallenge.region;

import com.igorms.cdcchallenge.shared.UniqueValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
public class State {

    @Deprecated
    public State() {}

    public State(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    private Country country;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static State fromRequest(NewStateRequest newStateRequest, EntityManager entityManager) {
        Country country = Optional.ofNullable(entityManager.find(Country.class, newStateRequest.getCountryId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new State(newStateRequest.getName(), country);
    }
}
