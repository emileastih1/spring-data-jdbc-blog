package com.eas.blog.content.impl;

import com.eas.blog.account.domain.Author;
import com.eas.blog.content.domain.Post;
import com.eas.blog.content.dto.PostCreateRequest;
import com.eas.blog.content.dto.PostView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mainAuthorId", source = "mainAuthorId")
    Post toPost(PostCreateRequest postCreateRequest, AggregateReference<Author, Integer> mainAuthorId);

    PostView toPostView(Post saved);
}
