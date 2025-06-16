package com.eas.blog.content.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("postlike")
public class Like {
    @Id
    Integer id;

//    @Transient
//    private Post post;
//
//    @Transient
//    private User user;

    private final Integer user_id;


}
