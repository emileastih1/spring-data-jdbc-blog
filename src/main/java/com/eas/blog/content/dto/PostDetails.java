package com.eas.blog.content.dto;

import com.eas.blog.account.domain.Author;
import com.eas.blog.content.domain.Post;

public record PostDetails(Post post, Author author) {
}
