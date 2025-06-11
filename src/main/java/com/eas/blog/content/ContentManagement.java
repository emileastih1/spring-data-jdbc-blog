package com.eas.blog.content;

import com.eas.blog.content.dto.PostSimpleView;
import com.eas.blog.content.domain.Comment;
import com.eas.blog.content.domain.Post;
import com.eas.blog.content.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentManagement {
    public final PostRepository postRepository;

    public Post comment(Comment comment) {
        return null;
    }

    public List<PostSimpleView> findAllPostsForAuthor(Integer authorId) {
        return postRepository.findByAuthorId(AggregateReference.to(authorId))
                .stream()
                .map(post -> new PostSimpleView(post.getId(), post.getTitle(), post.getContent(), post.getPublishedOn(), post.getAuthorId().getId()))
                .toList();
    }
}
