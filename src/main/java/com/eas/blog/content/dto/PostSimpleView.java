package com.eas.blog.content.dto;

import java.time.LocalDateTime;

public record PostSimpleView(Integer postId, String title, String content, LocalDateTime publishedOn, Integer authorId) {
}
