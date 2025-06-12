package com.eas.blog.account.exposition;

import com.eas.blog.account.api.AuthorManagementApi;
import com.eas.blog.account.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
class AuthorApi {
    private final AuthorManagementApi authorManagement;

    @GetMapping
    List<Author> findAllAuthors() {
        return authorManagement.findAll();
    }

    @PostMapping
    Author save(@RequestBody Author author) {
        return authorManagement.save(author);
    }
}
