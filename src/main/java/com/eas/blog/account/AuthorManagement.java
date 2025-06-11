package com.eas.blog.account;

import com.eas.blog.account.application.dto.AuthorBasicInfo;
import com.eas.blog.account.application.exception.EntityNotFoundException;
import com.eas.blog.account.application.mappers.AuthorMapper;
import com.eas.blog.account.domain.Author;
import com.eas.blog.account.infrastructure.AuthorRepository;
import com.eas.blog.content.ContentManagement;
import com.eas.blog.content.dto.PostSimpleView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorManagement {
    private final AuthorRepository authorRepository;
    private final AuthorMapper  authorMapper;
    private final ContentManagement contentManagement;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public AuthorBasicInfo findById(Integer authorId) {
        return authorRepository.findById(authorId)
                .map(authorMapper::toAuthorBasicInfo)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found", authorId));

    }

    public List<PostSimpleView> retrievePostsByAuthor(Integer authorId) {
        return contentManagement.findAllPostsForAuthor(authorId);
    }



}
