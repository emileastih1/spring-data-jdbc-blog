package com.eas.blogjdbc.post.domain;

import com.eas.blogjdbc.user.domain.Author;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table("POST")
public class Post {
    @Id
    Integer id;
    String title;
    String content;
    LocalDateTime publishedOn;
    LocalDateTime updatedOn;

    // This is how we handle the relationship within an aggregate root
    @MappedCollection(idColumn = "POST_ID")
    Set<Comment> comments = new HashSet<>();

    // This is how we handle the relationship within an aggregate root
    @MappedCollection(idColumn = "POST_ID")
    Set<Like> likes = new HashSet<>();

    // This is how we handle the relationship between aggregates
    AggregateReference<Author,Integer> author;

    public Post(String title,String content, AggregateReference<Author,Integer> author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.publishedOn = LocalDateTime.now();
    }


    public void addComments(List<Comment> comments) {
        comments.forEach(this::addComment);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        //comment.setPost(this);
    }

    public void like(Like like){
        likes.add(like);
    }
}
