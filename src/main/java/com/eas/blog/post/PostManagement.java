package com.eas.blog.post;

import com.eas.blog.post.domain.Comment;
import com.eas.blog.post.domain.Post;
import com.eas.blog.post.infrastructure.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostManagement {
    public final PostRepository postRepository;

    Post comment(Comment comment) {
        return null;
    }
}
