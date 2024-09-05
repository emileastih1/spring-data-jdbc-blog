package com.eas.blogjdbc.post.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * This comment class is not an aggregate, in fact it belongs to a {@link Post}
 * because a {@link Comment} cannot live on its own!
 */
@Data
@Table("COMMENT")
public final class Comment {
    @Id
    Integer id;

    private String content;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;

    @Column(value = "USER_ID")
    private Integer userId;

    //We marked this as transient because we dont want to persist this to the database
    @Transient
    private Post post;

    public Comment(String content, Integer userId) {
        this.content = content;
        this.publishedOn = LocalDateTime.now();
        this.userId = userId;
    }

}
