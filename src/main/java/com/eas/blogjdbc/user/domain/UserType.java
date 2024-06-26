package com.eas.blogjdbc.user.domain;

public enum UserType {
    USER(0),
    AUTHOR(1);

    public final int value;

    UserType(int type) {
        this.value = type;
    }
}
