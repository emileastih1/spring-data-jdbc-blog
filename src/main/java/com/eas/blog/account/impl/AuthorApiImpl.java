package com.eas.blog.account.impl;

import com.eas.blog.account.AuthorApi;
import com.eas.blog.account.application.AuthorManagement;
import com.eas.blog.account.application.dto.AuthorBasicInfo;
import com.eas.blog.account.domain.Author;
import com.eas.blog.content.ContentApi;
import com.eas.blog.content.dto.PostSimpleView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorApiImpl implements AuthorApi {
    private final AuthorManagement authorManagement;
    private final ContentApi contentManagementApi;

    public List<AuthorBasicInfo> findAll() {
        return authorManagement.findAll();
    }

    public AuthorBasicInfo save(Author author) {
        return authorManagement.save(author);
    }

    public AuthorBasicInfo findById(Integer authorId) {
        return authorManagement.findById(authorId);

    }

    public List<PostSimpleView> retrievePostsByAuthor(Integer authorId) {
        return contentManagementApi.findAllPostsForAuthor(authorId);
    }

    public List<PostSimpleView> retrieveLatestPostsByAuthor(Integer authorId, Integer limit) {
        return contentManagementApi.findLatestPostsByAuthor(authorId, limit);
    }


}
