package com.eas.blog.user.infrastructure;

import com.eas.blog.user.domain.Author;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Integer> {

}
