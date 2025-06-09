package com.eas.blog.post.application.dto;

import com.eas.blog.post.domain.Post;
import com.eas.blog.user.domain.Author;

public record PostDetails(Post post, Author author) {
}
