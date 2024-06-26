package com.eas.blogjdbc.post.application.dto;

import com.eas.blogjdbc.post.domain.Post;
import com.eas.blogjdbc.user.domain.Author;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Embedded;

public class PostViewNew extends Post {
    public PostViewNew(String title, String content, AggregateReference<Author, Integer> author) {
        super(title, content, author);
    }

    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY, prefix = "author_view_")
    Author authorView;
}
