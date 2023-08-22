package com.igorms.cdcchallenge.purchase;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
    @Transactional
    public void makePurchase(@Valid @RequestBody PurchaseRequest purchaseRequest) {
        entityManager.persist(Purchase.fromRequest(purchaseRequest, entityManager));
    }

    @GetMapping("purchase")
    public ResponseEntity<PurchaseResponse> getPurchase(@RequestParam Long id) {
        Purchase purchase = entityManager.find(Purchase.class, id);

        PurchaseResponse purchaseResponse = PurchaseResponse.fromEntity(purchase);

        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }

}
