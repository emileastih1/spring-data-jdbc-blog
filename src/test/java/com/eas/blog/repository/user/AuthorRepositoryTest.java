package com.eas.blog.repository.user;

import com.eas.blog.user.infrastructure.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authors;

    @Test
    void shouldReturnAllAuthors() {
        long count = authors.findAll().size();
        assertEquals(2, count);
    }
}