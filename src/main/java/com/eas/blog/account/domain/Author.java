package com.eas.blog.account.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Getter
public class Author {
    @Id
    private Integer id;
    private final String name;
    private final String email;
    private final String bio;
    private final String profilePicture;
    private final LocalDateTime createdOn;

    // Constructor for inserts
    public Author(String name, String email, String bio, String profilePicture) {

        Assert.notNull(name, "Name must not be null");

        this.name = name;
        this.email = email;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.createdOn = LocalDateTime.now();
    }

    // Constructor for reads (persistence constructor)
    @PersistenceCreator
    public Author(Integer id, String name, String email, String bio, String profilePicture, LocalDateTime createdOn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.profilePicture = profilePicture;
        this.createdOn = createdOn;
    }
}
