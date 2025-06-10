package com.eas.blog.repository.post;

import com.eas.blog.post.domain.Comment;
import com.eas.blog.post.domain.Like;
import com.eas.blog.post.domain.Post;
import com.eas.blog.post.infrastructure.PostRepository;
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
        Post post = mockPost();

        // When
        assertNull(post.getId(), "Post ID should be null before saving");
        Post savedPost = posts.save(post);

        // Then
        assertNotNull(savedPost.getId(), "Post ID should be set after saving");
        //assert that the new post is linked to the same author
        assertEquals(1, savedPost.getAuthor().getId());
    }


    @Test
    void shouldPostWithComments() {
        //Given
        Integer userId = 1;

        Post post = mockPost();
        post.addComments(List.of(
                new Comment("test comment", userId),
                new Comment("test comment 2", userId))
        );
        Post savedPost = posts.save(post);

        Post p = posts.findById(savedPost.getId()).orElse(null);
        assertNotNull(p);
        assertEquals(2, p.getComments().size());
    }

    @Test
    void shouldPostWithNoCommentsAndNoLikes() {
        Post post = mockPost();
        Post savedPost = posts.save(post);
        Post p = posts.findById(savedPost.getId()).orElse(null);
        assertNotNull(p);
        assertEquals(0, p.getComments().size());
        assertEquals(0, p.getLikes().size());
    }

    @Test
    void should_like_post_only_once_when_user_likes_multiple_times() {
        Integer userId = 1;

        Post post = mockPost();
        post.like(new Like(userId));
        post.like(new Like(userId));
        post.like(new Like(userId));
        Post savedPost = posts.save(post);

        Post p = posts.findById(savedPost.getId()).orElse(null);
        assertNotNull(p);
        assertEquals(0, p.getComments().size());
        assertEquals(1, p.getLikes().size());
    }

    private static Post mockPost() {
        return new Post("TEST", "...", AggregateReference.to(1));
    }

}