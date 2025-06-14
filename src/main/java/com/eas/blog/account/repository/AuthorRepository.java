package com.eas.blog.account.repository;

import com.eas.blog.account.domain.Author;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Integer> {

}
