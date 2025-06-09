package com.eas.blog.user.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
public class Author {
    @Id
    private Integer id;
    private String name;
    private String email;
    private String bio;
    private String profilePicture;
    private LocalDateTime createdOn;
}
