package com.igorms.cdcchallenge.purchase;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class PurchaseController {

    @PersistenceContext
    private EntityManager entityManager;

    public PurchaseController(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @PostMapping("purchase")
    public void makePurchase(@Valid @RequestBody PurchaseRequest purchaseRequest) {
        entityManager.persist(Purchase.fromRequest(purchaseRequest, entityManager));
    }

}
