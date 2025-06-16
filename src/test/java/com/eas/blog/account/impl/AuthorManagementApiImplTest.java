package com.eas.blog.account.impl;

import com.eas.blog.config.TestcontainersPostgresInitializer;
import com.eas.blog.content.dto.PostSimpleView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = TestcontainersPostgresInitializer.class)
class AuthorManagementApiImplTest {

    @Autowired
    AuthorApiImpl apiImplementation;

    @Test
    void shouldRetrieveLatestPostsByAuthorWithLimit() {
        int authorId = 2;
        int limit = 2;

        List<PostSimpleView> result = apiImplementation.retrieveLatestPostsByAuthor(authorId, limit);

        assertThat(result)
                .hasSize(limit)
                .extracting(PostSimpleView::title)
                .containsExactly(
                        "GCP Cloud Explained",
                        "React Concepts Explained"
                );
    }

    @Test
    void when_no_limit_specified_should_retrieve_all_posts_by_author() {
        int authorId = 2;

        List<PostSimpleView> result = apiImplementation.retrieveLatestPostsByAuthor(authorId, null);

        assertThat(result)
                .hasSize(5)
                .extracting(PostSimpleView::title)
                .containsExactly(
                        "GCP Cloud Explained",
                        "React Concepts Explained",
                        "Java Lambda Explained",
                        "Java Optional Explained",
                        "Java Streams Explained"
                );
    }

    @Test
    void shouldReturnEmptyListWhenNoPostsExistForAuthor() {
        List<PostSimpleView> result = apiImplementation.retrieveLatestPostsByAuthor(-999, 5);
        assertThat(result).isEmpty();
    }

}
