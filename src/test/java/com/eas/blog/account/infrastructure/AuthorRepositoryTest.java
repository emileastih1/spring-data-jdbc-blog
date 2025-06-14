package com.eas.blog.account.infrastructure;

import com.eas.blog.account.domain.Author;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJdbcTest
@Transactional
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @BeforeEach
    void setup() {
        Author author = new Author(
                "Emile",
                "emile@example.com",
                "Tech Lead & Architect",
                "profile.png"
        );
        authorRepository.save(author);
    }

    @Test
    void shouldReturnAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        Assertions.assertThat(authors)
                .isNotEmpty();
    }

    @Test
    void should_return_all_authors_and_check_result() {
        List<Author> authors = authorRepository.findAll();
        Assertions.assertThat(authors)
                .isNotEmpty()
                .extracting(Author::getName)
                .containsAnyOf("Emile");
    }
}