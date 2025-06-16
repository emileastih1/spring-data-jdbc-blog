package com.eas.blog.content.impl;

import com.eas.blog.account.AuthorApi;
import com.eas.blog.config.TestcontainersPostgresInitializer;
import com.eas.blog.content.dto.PostSimpleView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(initializers = TestcontainersPostgresInitializer.class)
class ContentManagementApiImplTest {

    @Autowired
    AuthorApi authorManagementApi;

    @Test
    void given_authorId_should_return_all_posts_for_author() {
        Integer authorId = 1;

        List<PostSimpleView> posts = authorManagementApi.retrievePostsByAuthor(authorId);

        assertThat(posts).isNotEmpty();
        assertThat(posts).allMatch(p -> Objects.equals(p.authorId(), authorId));
    }
}