package com.eas.blog.post.domain;

import com.eas.blog.user.domain.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("POSTLIKE")
public class Like {
    @Id
    Integer id;

    @Transient
    private Post post;

    @Transient
    private User user;

    private final Integer user_id;


}
