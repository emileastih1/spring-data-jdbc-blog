package com.eas.blogjdbc.user.domain;

public class Author extends User {
    public Author(String firstname, String lastname, String email, String username, String profilePicture){
        super(firstname, lastname, email, username, UserType.AUTHOR.value, profilePicture);
    }
}
