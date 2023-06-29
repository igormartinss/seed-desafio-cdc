package com.igorms.cdcchallenge.region;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("state")
    public ResponseEntity<List<StateResponse>> findStates() {
        Query query = entityManager.createQuery("select s from State s");

        List<State> states = query.getResultList();
        List<StateResponse> statesResponse = new ArrayList<StateResponse>();

        states.forEach(state -> statesResponse.add(StateResponse.fromEntity(state)));

        return new ResponseEntity<>(statesResponse, HttpStatus.OK);
    }
}
