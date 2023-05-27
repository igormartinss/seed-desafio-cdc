package com.igorms.cdcchallenge.author;

import javax.validation.constraints.NotBlank;

public class AuthorResponse {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String description;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public static AuthorResponse fromAuthor(Author author){
        AuthorResponse authorResponse = new AuthorResponse();
        authorResponse.id = author.getId();
        authorResponse.name = author.getName();
        authorResponse.email = author.getEmail();
        authorResponse.description = author.getDescription();
        return authorResponse;
    }

}
