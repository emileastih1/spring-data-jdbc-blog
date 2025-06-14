package com.eas.blog.account;

import com.eas.blog.account.application.dto.AuthorBasicInfo;
import com.eas.blog.account.domain.Author;
import com.eas.blog.content.dto.PostSimpleView;

import java.util.List;

public interface AuthorApi {

    List<AuthorBasicInfo> findAll();

    AuthorBasicInfo save(Author author);

    AuthorBasicInfo findById(Integer authorId);

    List<PostSimpleView> retrievePostsByAuthor(Integer authorId);

    List<PostSimpleView> retrieveLatestPostsByAuthor(Integer authorId, Integer limit);


}
