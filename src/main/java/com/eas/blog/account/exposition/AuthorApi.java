package com.eas.blog.account.exposition;

import com.eas.blog.account.AuthorManagement;
import com.eas.blog.account.domain.Author;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
class AuthorApi {
    private AuthorManagement authorManagement;

    @GetMapping
    List<Author> findAllAuthors() {
        return authorManagement.findAll();
    }

    @PostMapping
    Author save(@RequestBody Author author) {
        return authorManagement.save(author);
    }
}
