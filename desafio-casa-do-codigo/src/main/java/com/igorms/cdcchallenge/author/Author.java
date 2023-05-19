package com.igorms.cdcchallenge.author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Author {

    @Deprecated
    public Author() {
    }

    public Author (String name, String email, String description) {
        this.description = description;
        this.email = email;
        this.name = name;
        this.createdAt = LocalDateTime.now();
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
}
