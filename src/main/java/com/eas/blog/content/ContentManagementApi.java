package com.eas.blog.content;

import com.eas.blog.content.dto.PostSimpleView;

import java.util.List;

public interface ContentManagementApi {
    List<PostSimpleView> findAllPostsForAuthor(Integer authorId);
    List<PostSimpleView> findLatestPostsByAuthor(Integer authorId, Integer limit);
}
