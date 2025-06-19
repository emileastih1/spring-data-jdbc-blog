package com.eas.blog.account.application;

import com.eas.blog.account.application.dto.AuthorCreateRequest;
import com.eas.blog.account.domain.Author;
import com.eas.blog.account.repository.AuthorRepository;
import com.eas.blog.config.TestcontainersPostgresInitializer;
import com.eas.blog.content.domain.Post;
import com.eas.blog.content.dto.PostCreateRequest;
import com.eas.blog.content.dto.PostView;
import com.eas.blog.content.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = TestcontainersPostgresInitializer.class)
class AuthorPostOrchestratorTest {

    @Autowired
    AuthorPostOrchestrator authorPostOrchestrator;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void should_create_posts_with_main_author_and_editors() {
        AuthorCreateRequest authorCreateRequest = new AuthorCreateRequest("Loren", "loren@gmail.com");
        PostCreateRequest postCreateRequest = new PostCreateRequest("Complex Use Case", "Post Content");
        Set<AuthorCreateRequest> editors = Set.of(
                new AuthorCreateRequest("editor1_", "editor1@gmail.com"),
                new AuthorCreateRequest("editor2_", "editor2@gmail.com")
        );

        PostView result = authorPostOrchestrator.createPostWithMainAuthorAndEditors(authorCreateRequest, editors, postCreateRequest);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo("Complex Use Case");
        assertThat(result.getContent()).isEqualTo("Post Content");

        // And the post should exist in DB with main author and editors
        Post post = postRepository.findById(result.getId()).get();
        assertThat(post).isNotNull();


        assertThat(post.getMainAuthorId().getId()).isGreaterThan(0);
        assertThat(post.getEditors()).hasSize(2);

        // And authors are persisted
        List<Author> savedAuthors = authorRepository.findAll();
        assertThat(savedAuthors)
                .isNotNull()
                .extracting(Author::getName)
                .containsAll(List.of("Loren", "editor1_", "editor2_"));
    }

}