package com.eas.blog.content.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;


@Getter
public class PostEditor {
    @Id
    private Integer id;
    private Integer editorId;

    public PostEditor(Integer editorId) {
        this.editorId = editorId;
    }
}
