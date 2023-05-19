package com.igorms.cdcchallenge.author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewAuthorRequest {

    @NotBlank
    public final String name;

    @NotBlank
    @Email
    public final String email;

    @NotBlank
    @Size(max = 400, min = 0)
    public final String description;

    public NewAuthorRequest(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }
}
