package com.eas.blog.content.impl;

import com.eas.blog.content.ContentApi;
import com.eas.blog.content.domain.Comment;
import com.eas.blog.content.domain.Post;
import com.eas.blog.content.dto.PostSimpleView;
import com.eas.blog.content.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentApiImpl implements ContentApi {
    public final PostRepository postRepository;

    public Post comment(Comment comment) {
        return null;
    }

    public List<PostSimpleView> findAllPostsForAuthor(Integer authorId) {
        return postRepository.findByMainAuthorId(AggregateReference.to(authorId))
                .stream()
                .map(post -> new PostSimpleView(post.getId(), post.getTitle(), post.getContent(), post.getPublishedOn(), post.getMainAuthorId().getId()))
                .toList();
    }

    public List<PostSimpleView> findLatestPostsByAuthor(Integer authorId, Integer limit) {
        List<PostSimpleView> posts = postRepository.findSimpleViewsByAuthorOrderedByDateDesc(authorId);
        return Optional.ofNullable(limit)
                .map(l -> posts.stream().limit(l).toList())
                .orElse(posts);
    }
}
