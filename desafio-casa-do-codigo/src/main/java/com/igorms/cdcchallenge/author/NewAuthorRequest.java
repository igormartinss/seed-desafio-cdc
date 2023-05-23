package com.igorms.cdcchallenge.author;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewAuthorRequest {

    @NotBlank(message = "Name can't be blank")
    public final String name;

    @NotBlank(message = "Email can't be blank")
    @Email
    public final String email;

    @NotBlank(message = "Description can't be blank")
    @Size(max = 400, min = 0)
    public final String description;

    public NewAuthorRequest(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
    }

    public String getEmail() {
        return email;
    }
}
