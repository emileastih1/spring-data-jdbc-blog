package com.eas.blog.content.infrastructure;

import com.eas.blog.account.domain.Author;
import com.eas.blog.content.dto.PostView;
import com.eas.blog.content.dto.PostViewNew;
import com.eas.blog.content.domain.Post;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PostRepository extends ListCrudRepository<Post, Integer> {

    @Query("SELECT * FROM POST WHERE author_id = :id")
    List<Post> findByAuthorId(Integer id);

    List<Post> findByAuthorId(AggregateReference<Author, Integer> authorId);

    List<Post> findAllByAuthorId(Integer authorId);

    //List<Post> findByAuthorLastNameIgnoreCase(String lastName);

    @Query("""
            SELECT p.id, p.title, p.content, p.published_on,
                   a.id as author_id, a.first_name, a.last_name, a.email, a.username
            FROM post p
            JOIN author a ON p.author = a.id
            """)
    List<PostView> findAllPostsWithAuthorDetails();


    @Query("""
            SELECT p.id, p.title, p.content, p.published_on,
            a.id as author_id, a.first_name, a.last_name, a.email, a.username
            FROM post p
            JOIN author a ON p.author = a.id
            """)
    List<PostViewNew> findAllPostsWithAuthorDetailsNew();


}
