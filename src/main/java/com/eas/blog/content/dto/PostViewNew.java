package com.eas.blog.content.dto;

import com.eas.blog.account.domain.Author;
import com.eas.blog.content.domain.Post;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDateTime;

public class PostViewNew extends Post {
    public PostViewNew(String title, String content, AggregateReference<Author, Integer> author) {
        super(title, content, LocalDateTime.now(), author);
    }

    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY, prefix = "author_view_")
    Author authorView;
}
