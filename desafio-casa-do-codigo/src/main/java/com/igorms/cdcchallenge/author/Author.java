package com.igorms.cdcchallenge.author;

import com.igorms.cdcchallenge.shared.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Author {

    @Deprecated
    public Author() {
    }

    public Author(String name, String email, String description) {
        this.description = description;
        this.email = email;
        this.name = name;
        this.createdAt = LocalDateTime.now();
    }

    public static Author fromRequest(NewAuthorRequest newAuthorRequest) {
        return new Author(newAuthorRequest.name, newAuthorRequest.email, newAuthorRequest.description);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 400, min = 0)
    private String description;

    private LocalDateTime createdAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
