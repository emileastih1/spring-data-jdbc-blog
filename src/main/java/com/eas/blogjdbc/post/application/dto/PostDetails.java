package com.eas.blogjdbc.post.application.dto;

import com.eas.blogjdbc.post.domain.Post;
import com.eas.blogjdbc.user.domain.Author;

public record PostDetails(Post post, Author author) { }
