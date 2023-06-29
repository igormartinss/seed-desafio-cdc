package com.igorms.cdcchallenge.region;

import com.igorms.cdcchallenge.book.Book;
import com.igorms.cdcchallenge.book.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("countries")
    public ResponseEntity<List<CountryResponse>> findCountries() {
        Query query = entityManager.createQuery("select c from Country c");

        List<Country> countries = query.getResultList();
        List<CountryResponse> countriesResponse = new ArrayList<>();
        countries.forEach(country -> countriesResponse.add(CountryResponse.fromEntity(country)));

        return new ResponseEntity<>(countriesResponse, HttpStatus.OK);
    }

}
