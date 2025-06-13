package com.eas.blog.content;

import com.eas.blog.account.domain.Author;
import com.eas.blog.account.infrastructure.AuthorRepository;
import com.eas.blog.content.domain.Post;
import com.eas.blog.content.domain.PostEditor;
import com.eas.blog.content.infrastructure.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostEditorIntegrationTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void shouldSaveAndRetrievePostWithEditors() {
        // Given: 2 authors
        Author mainAuthor = authorRepository.save(new Author("Main Author", "main@example.com", "Main Bio", "main.jpg"));
        Author editor1 = authorRepository.save(new Author("Editor One", "editor1@example.com", "Editor Bio", "editor1.jpg"));
        Author editor2 = authorRepository.save(new Author("Editor Two", "editor2@example.com", "Editor Bio", "editor2.jpg"));

        // And: a post with editors
        Post post = new Post("A collaborative post", "Some content", LocalDateTime.now(), AggregateReference.to(mainAuthor.getId()));

        Set<PostEditor> editors = Set.of(
                new PostEditor(editor1.getId()),
                new PostEditor(editor2.getId())
        );

        post.setEditors(editors);

        // When: we save the post
        Post saved = postRepository.save(post);

        // Then: we retrieve it and check editors
        Optional<Post> retrieved = postRepository.findById(saved.getId());
        assertThat(retrieved).isPresent();

        Post loaded = retrieved.get();
        assertThat(loaded.getEditors()).hasSize(2);
        assertThat(loaded.getEditors())
                .extracting(PostEditor::getEditorId)
                .containsExactlyInAnyOrder(editor1.getId(), editor2.getId());
    }
}

