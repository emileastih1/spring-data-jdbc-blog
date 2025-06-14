package com.eas.blog.account;

import com.eas.blog.account.domain.User;

import java.util.List;

public interface UserApi {

    List<User> findAll();

    User save(User user);

    User findOne(Integer id);
}
