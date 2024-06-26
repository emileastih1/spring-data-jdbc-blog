package com.eas.blogjdbc.repository;

import com.eas.blogjdbc.post.domain.Comment;
import com.eas.blogjdbc.post.domain.Post;
import com.eas.blogjdbc.post.infrastructure.PostRepository;
import com.eas.blogjdbc.user.domain.Author;
import com.eas.blogjdbc.user.infrastructure.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    AuthorRepository authorRepository;

    AggregateReference<Author,Integer> author;

    @BeforeEach
    void setUp() {
        author = AggregateReference.to(authorRepository.save(new Author(null, "Dan", "Vega", "danvega@gmail.com", "dvega")).getId());
    }

    @Test
    void shouldSaveValidPost() {
        Post post = new Post( "TEST", "...",author);
        assertNull(post.getId());
        Post reloaded = posts.save(post);
        assertNotNull(reloaded.getId());
    }

    @Test
    void shouldSaveValidPostWithoutAuthor() {
        Post post = new Post( "TEST", "...",null);
        assertNull(post.getId());
        Post reloaded = posts.save(post);
        assertNotNull(reloaded.getId());
        assertNull(reloaded.getAuthor());
    }

    @Test
    void shouldPostWithComments() {
        Post post = new Post( "TEST", "...",null);
        post.addComments(List.of(new Comment("test comment", null),new Comment("test comment 2", null)));
        posts.save(post);

        Post p = posts.findById(post.getId()).orElse(null);
        assertNotNull(p);
        assertNotNull(p.getId());
        assertEquals(2,p.getComments().size());
    }

    @Test
    void shouldPostWithNoCommentsReturns0AndNotNull() {
        Post post = new Post( "TEST", "...",null);
        posts.save(post);
        Post p = posts.findById(post.getId()).orElse(null);
        assertNotNull(p);
        assertEquals(0,p.getComments().size());
    }

}