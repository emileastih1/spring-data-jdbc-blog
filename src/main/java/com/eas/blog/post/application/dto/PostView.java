package com.eas.blog.post.application.dto;


import com.eas.blog.user.domain.Author;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostView {
    Integer id;
    String title;
    String content;
    LocalDateTime publishedOn;
    Author author;

    public PostView(Integer id, String title, String content, LocalDateTime publishedOn, Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishedOn = publishedOn;
        this.author = author;
    }
}
