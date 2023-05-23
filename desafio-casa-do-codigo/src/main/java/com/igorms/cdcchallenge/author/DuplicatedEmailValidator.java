package com.igorms.cdcchallenge.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class DuplicatedEmailValidator implements Validator {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NewAuthorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        NewAuthorRequest request = (NewAuthorRequest) target;
        Optional<Author> author = authorRepository.findByEmail(request.getEmail());
        if (author.isPresent()) {
            errors.rejectValue("email", null, "Email already exists");
        }
    }


}
