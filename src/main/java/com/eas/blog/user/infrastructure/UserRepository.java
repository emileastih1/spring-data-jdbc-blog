package com.eas.blog.user.infrastructure;

import com.eas.blog.user.domain.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, Integer> {
}
