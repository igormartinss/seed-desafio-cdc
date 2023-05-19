package com.igorms.cdcchallenge.author;

import javax.validation.constraints.NotBlank;

public class NewAuthorRequest {

    @NotBlank
    public final String name;

    @NotBlank
    public final String email;

    @NotBlank
    public final String description;

    public NewAuthorRequest(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
