package com.igorms.cdcchallenge.category;

import com.igorms.cdcchallenge.author.NewAuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DuplicatedNameValidator implements Validator {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public boolean supports(Class<?> clazz) {
        return NewCategoryRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NewCategoryRequest newCategoryRequest = (NewCategoryRequest) target;

        if(categoryRepository.findCategoryByName(newCategoryRequest.getName()).isPresent()) {
            errors.rejectValue("name", null, "Name already exists");
        }

    }
}
