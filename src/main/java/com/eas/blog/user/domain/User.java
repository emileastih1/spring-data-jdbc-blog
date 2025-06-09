package com.eas.blog.user.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("USERS")
@Getter
public class User {
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private LocalDateTime createdOn;
}
