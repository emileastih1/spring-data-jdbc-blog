package com.eas.blogjdbc.user.infrastructure;

import com.eas.blogjdbc.user.domain.Author;
import com.eas.blogjdbc.user.domain.User;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends ListCrudRepository<Author, Integer> {
}
