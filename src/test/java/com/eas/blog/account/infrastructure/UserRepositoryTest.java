package com.eas.blog.account.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findAll() {
        Assertions.assertEquals(3, userRepository.findAll().size());
    }

    @Test
    public void findById() {
        Assertions.assertEquals(1, userRepository.findById(1).get().getId());
    }
}
