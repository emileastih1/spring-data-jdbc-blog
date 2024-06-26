package com.eas.blogjdbc.post.application.dto;


import com.eas.blogjdbc.user.domain.Author;

import java.time.LocalDateTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
