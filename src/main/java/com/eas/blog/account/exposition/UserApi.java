package com.eas.blog.account.exposition;

import com.eas.blog.account.api.UserManagementApi;
import com.eas.blog.account.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
class UserApi {

    private final UserManagementApi userManagement;

    @GetMapping
    List<User> findAllAuthors() {
        return userManagement.findAll();
    }

    @PostMapping
    User save(@RequestBody User user) {
        return userManagement.save(user);
    }
}
