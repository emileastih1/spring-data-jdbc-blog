package com.eas.blog.account.application.mappers;

import com.eas.blog.account.application.dto.AuthorBasicInfo;
import com.eas.blog.account.domain.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorBasicInfo toAuthorBasicInfo(Author author);
}
