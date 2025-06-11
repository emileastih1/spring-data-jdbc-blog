package com.eas.blog.content.domain;

import com.eas.blog.account.domain.Author;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

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

    // This is how we handle the relationship (ONE_TO_MANY) within an aggregate root
    @MappedCollection(idColumn = "POST_ID")
    Set<Comment> comments = new HashSet<>();

    // This is how we handle the relationship (ONE_TO_MANY) within an aggregate root
    @MappedCollection(idColumn = "POST_ID")
    Set<Like> likes = new HashSet<>();

    /**
     *  This is how we handle the relationship between aggregates
     *  This is a one directional relation from Post to Author, because we consider
     *  that a post does not exist without an Author.
     *
     *  In order for spring data jdbc to understand that this field references another aggregate,
     *  you need to name it xxx_id
     */
    AggregateReference<Author, Integer> authorId;

    public Post(String title, String content, AggregateReference<Author, Integer> authorId) {

        Assert.notNull(title, "Title must not be null");
        Assert.notNull(content, "Content must not be null");
        Assert.notNull(authorId, "Author must not be null");

        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.publishedOn = LocalDateTime.now();
    }

    public void addComments(List<Comment> comments) {
        comments.forEach(this::addComment);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        //comment.setPost(this);
    }

    public void like(Like like) {
        likes.add(like);
    }
}
