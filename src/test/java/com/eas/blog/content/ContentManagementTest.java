package com.eas.blog.content;

import com.eas.blog.account.api.AuthorManagementApi;
import com.eas.blog.content.dto.PostSimpleView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContentManagementTest {

    @Autowired
    AuthorManagementApi authorManagement;

    @Test
    void given_authorId_should_return_all_posts_for_author() {
        Integer authorId = 1;

        List<PostSimpleView> posts = authorManagement.retrievePostsByAuthor(authorId);

        assertThat(posts).isNotEmpty();
        assertThat(posts).allMatch(p -> Objects.equals(p.authorId(), authorId));
    }

}