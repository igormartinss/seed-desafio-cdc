package com.igorms.cdcchallenge.purchase.coupon;

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
public class CouponController {

    @PersistenceContext
    private EntityManager entityManager;

    public CouponController(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    @PostMapping("/coupon")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void create(@Valid @RequestBody CouponRequest couponRequest) {
        entityManager.persist(Coupon.fromRequest(couponRequest));
    }

    @GetMapping("/coupon")
    @ResponseStatus(HttpStatus.OK)
    public List<CouponResponse> find() {
        Query query = entityManager.createQuery("select c from Coupon c");

        List<Coupon> coupons = query.getResultList();

        List<CouponResponse> response = new ArrayList<>();

        coupons.forEach(coupon -> response.add(CouponResponse.fromEntity(coupon)));

        return response;
    }
}
