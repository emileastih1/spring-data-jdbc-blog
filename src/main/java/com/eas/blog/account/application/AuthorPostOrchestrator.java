package com.eas.blog.account.application;

import com.eas.blog.account.application.dto.AuthorBasicInfo;
import com.eas.blog.account.application.dto.AuthorCreateRequest;
import com.eas.blog.account.application.mappers.AuthorMapper;
import com.eas.blog.content.ContentApi;
import com.eas.blog.content.dto.PostCreateRequest;
import com.eas.blog.content.dto.PostView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorPostOrchestrator {
    private final AuthorManagement authorManagement;
    private final ContentApi contentApi;
    private final AuthorMapper authorMapper;

    public PostView createPostWithMainAuthorAndEditors(AuthorCreateRequest mainAuthor, Set<AuthorCreateRequest> editors, PostCreateRequest postCreateRequest) {
        AuthorBasicInfo savedMainAuthor = authorManagement.save(authorMapper.toAuthor(mainAuthor));

        List<Integer> savedEditors = editors.stream()
                .map(authorMapper::toAuthor)
                .map(authorManagement::save)
                .map(AuthorBasicInfo::id)
                .toList();

        return contentApi.createPostForAuthorWithEditors(savedMainAuthor.id(), savedEditors, postCreateRequest);
    }
}
