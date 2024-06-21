package dev.danvega.blogjdbc.dto;

import dev.danvega.blogjdbc.model.Author;
import dev.danvega.blogjdbc.model.Post;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Embedded;

public class PostViewNew extends Post {
    public PostViewNew(String title, String content, AggregateReference<Author, Integer> author) {
        super(title, content, author);
    }

    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY, prefix = "author_view_")
    Author authorView;
}
