package com.eas.blog.content.dto;

import com.eas.blog.content.domain.Post;
import com.eas.blog.account.domain.Author;

public record PostDetails(Post post, Author author) {
}
