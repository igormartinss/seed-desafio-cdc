package com.igorms.cdcchallenge.region;

import com.igorms.cdcchallenge.shared.UniqueValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    private String name;

    @NotBlank
    @ManyToOne
    private Country country;

    public static State fromRequest(NewStateRequest newStateRequest, EntityManager entityManager) {
        Country country = Optional.ofNullable(entityManager.find(Country.class, newStateRequest.getCountryId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new State(newStateRequest.getName(), country);
    }
}
