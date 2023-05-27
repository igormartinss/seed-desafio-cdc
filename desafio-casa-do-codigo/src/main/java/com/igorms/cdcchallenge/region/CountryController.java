package com.igorms.cdcchallenge.region;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CountryController {

    @PersistenceContext
    private EntityManager entityManager;

    public CountryController(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @PostMapping("countries")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid NewCountryRequest newCountryRequest) {
        entityManager.persist(Country.fromRequest(newCountryRequest));

    }

}
