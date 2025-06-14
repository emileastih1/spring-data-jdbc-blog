package com.eas.blog.account.exposition;

import com.eas.blog.account.domain.User;
import com.eas.blog.account.impl.UserApiImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class UserController {

    private final UserApiImpl userManagement;

    @GetMapping
    List<User> findAllAuthors() {
        return userManagement.findAll();
    }

    @PostMapping
    User save(@RequestBody User user) {
        return userManagement.save(user);
    }
}
