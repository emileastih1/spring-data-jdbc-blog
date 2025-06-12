package com.eas.blog.content.infrastructure;

import com.eas.blog.account.AuthorManagementApi;
import com.eas.blog.content.domain.Comment;
import com.eas.blog.content.domain.Like;
import com.eas.blog.content.domain.Post;
import com.eas.blog.account.application.dto.AuthorBasicInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository posts;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorManagementApi authorManagementApi;

    @Test
    void shouldSaveValidPostForAuthor() {
        Post post = mockPost();

        // When
        assertNull(post.getId(), "Post ID should be null before saving");
        Post savedPost = posts.save(post);

        // Then
        assertNotNull(savedPost.getId(), "Post ID should be set after saving");
        //assert that the new post is linked to the same author
        assertEquals(1, savedPost.getAuthorId().getId());
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

    @Test
    void should_retrieve_post_author(){
        AuthorBasicInfo authorBasicInfo =
                postRepository.findById(1)
                .map(Post::getAuthorId)
                .map(AggregateReference ::getId)
                .map(authorManagementApi::findById)
                .orElse(null);

        assertNotNull(authorBasicInfo, "Author should not be null");
        assertEquals(1, authorBasicInfo.id());
        assertEquals("John Doe", authorBasicInfo.name());
        assertEquals("john@example.com", authorBasicInfo.email());
    }

    @Test
    void given_valid_post_when_findById_should_fetch_comments_and_likes(){
        Post post = postRepository.findById(1)
                .orElse(null);

        assertNotNull(post, "post should not be null");
        assertNotNull(post.getComments(), "comments should not be null");
        assertNotNull(post.getLikes(), "likes should not be null");
    }


    @Test
    void given_authorId_should_return_all_posts_for_author_using_native_query() {
        Integer authorId = 1;

        List<Post> posts = postRepository.findByAuthorId(authorId);

        assertThat(posts).isNotEmpty();
        assertThat(posts).allMatch(p -> Objects.equals(p.getAuthorId().getId(), authorId));
    }

    @Test
    void given_authorId_should_return_all_posts_for_author_using_builtin_query_jdbc() {
        Integer authorId = 1;

        List<Post> posts = postRepository.findAllByAuthorId(authorId);

        assertThat(posts).isNotEmpty();
        assertThat(posts).allMatch(p -> Objects.equals(p.getAuthorId().getId(), authorId));
    }

    @Test
    void given_authorId_should_return_all_posts_for_author_using_aggregate_reference_jdbc() {
        Integer authorId = 1;

        List<Post> posts = postRepository.findByAuthorId(AggregateReference.to(authorId));

        assertThat(posts).isNotEmpty();
        assertThat(posts).allMatch(p -> Objects.equals(p.getAuthorId().getId(), authorId));
    }


    private static Post mockPost() {
        return new Post("TEST", "...", AggregateReference.to(1));
    }

}