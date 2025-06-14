package com.eas.blog.account.application;

import com.eas.blog.account.application.dto.AuthorBasicInfo;
import com.eas.blog.account.application.exception.EntityNotFoundException;
import com.eas.blog.account.application.mappers.AuthorMapper;
import com.eas.blog.account.domain.Author;
import com.eas.blog.account.infrastructure.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorManagement {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorBasicInfo> findAll() {
        return authorRepository
                .findAll()
                .stream()
                .map(authorMapper::toAuthorBasicInfo)
                .toList();
    }

    public AuthorBasicInfo save(Author author) {
        return Optional.of(authorRepository.save(author))
                .map(authorMapper::toAuthorBasicInfo)
                .orElseThrow(() -> new IllegalStateException("Author cannot be saved"));
    }

    public AuthorBasicInfo findById(Integer authorId) {
        return authorRepository.findById(authorId)
                .map(authorMapper::toAuthorBasicInfo)
                .orElseThrow(() -> new EntityNotFoundException("Author with id %d not found", authorId));

    }
}
