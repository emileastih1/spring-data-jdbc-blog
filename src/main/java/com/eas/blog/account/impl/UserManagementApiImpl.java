package com.eas.blog.account.impl;

import com.eas.blog.account.UserManagementApi;
import com.eas.blog.account.application.exception.EntityNotFoundException;
import com.eas.blog.account.domain.User;
import com.eas.blog.account.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserManagementApiImpl implements UserManagementApi {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findOne(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id %d not found", id));

    }
}
