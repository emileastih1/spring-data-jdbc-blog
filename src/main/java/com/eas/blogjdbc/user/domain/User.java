package com.eas.blogjdbc.user.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("APPUSER")
public class User {
    @Id
    private  Integer id;
    private  String firstname;
    private  String lastname;
    private  String email;
    private  String username;

    @Column(value = "USER_TYPE")
    private int userType;

    @Column(value = "PROFILE_PICTURE")
    private String profilePicture;

    public User(String firstname, String lastname, String email, String username, String profilePicture){
        this(firstname, lastname, email, username, UserType.USER.value, profilePicture);
    }

    protected User(String firstname, String lastname, String email, String username, int userType, String profilePicture) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.userType = userType;
        this.profilePicture = profilePicture;
    }

}

