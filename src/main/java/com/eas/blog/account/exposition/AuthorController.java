package com.eas.blog.account.exposition;

import com.eas.blog.account.application.AuthorManagement;
import com.eas.blog.account.application.dto.AuthorBasicInfo;
import com.eas.blog.account.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
class AuthorController {
    private final AuthorManagement authorManagement;

    @GetMapping
    List<AuthorBasicInfo> findAllAuthors() {
        return authorManagement.findAll();
    }

    @PostMapping
    AuthorBasicInfo save(@RequestBody Author author) {
        return authorManagement.save(author);
    }
}
