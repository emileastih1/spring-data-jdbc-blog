package com.eas.blog.repository.post;

import com.eas.blog.post.domain.Comment;
import com.eas.blog.post.domain.Post;
import com.eas.blog.post.infrastructure.PostRepository;
import com.eas.blog.user.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository posts;

    @Test
    void shouldSaveValidPostForAuthor() {
        // Given
        int authorId = 1;
        AggregateReference<Author, Integer> authorRef = AggregateReference.to(authorId);
        Post post = new Post("New post written", "...", authorRef);

        // When
        assertNull(post.getId(), "Post ID should be null before saving");
        Post savedPost = posts.save(post);

        // Then
        assertNotNull(savedPost.getId(), "Post ID should be set after saving");
        //assert that the new post is linked to the same author
        assertEquals(authorId, savedPost.getAuthor().getId());
    }


    @Test
    void shouldSaveValidPostWithoutAuthor() {
        Post post = new Post("TEST", "...", null);
        assertNull(post.getId());
        Post reloaded = posts.save(post);
        assertNotNull(reloaded.getId());
        assertNull(reloaded.getAuthor());
    }

    @Test
    void shouldPostWithComments() {
        //Given
        Integer userId = 1;

        Post post = new Post("TEST", "...", null);
        post.addComments(List.of(
                new Comment("test comment", userId),
                new Comment("test comment 2", userId))
        );
        Post savedPost = posts.save(post);

        Post p = posts.findById(savedPost.getId()).orElse(null);
        assertNotNull(p);
        assertNotNull(p.getId());
        assertEquals(2, p.getComments().size());
    }

    @Test
    void shouldPostWithNoCommentsReturns0AndNotNull() {
        Post post = new Post("TEST", "...", null);
        posts.save(post);
        Post p = posts.findById(post.getId()).orElse(null);
        assertNotNull(p);
        assertEquals(0, p.getComments().size());
    }

}