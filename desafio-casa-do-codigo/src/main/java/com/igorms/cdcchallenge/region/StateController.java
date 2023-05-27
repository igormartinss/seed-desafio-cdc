package com.igorms.cdcchallenge.region;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class StateController {

    @PersistenceContext
    private EntityManager entityManager;

    public StateController(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @PostMapping("/state")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid NewStateRequest newStateRequest) {
        entityManager.persist(State.fromRequest(newStateRequest, entityManager));
    }
}
