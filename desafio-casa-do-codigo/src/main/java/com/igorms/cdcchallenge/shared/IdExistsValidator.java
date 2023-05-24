package com.igorms.cdcchallenge.shared;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IdExistsValidator implements ConstraintValidator<IdExists, Object> {

    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(IdExists constraintAnnotation) {
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + " where id =:value");
        query.setParameter("value", value);
        List<?> result = query.getResultList();
        Assert.state(result.size() <= 1, "No entity found for this ID: " + value.toString());
        return !result.isEmpty();
    }
}
