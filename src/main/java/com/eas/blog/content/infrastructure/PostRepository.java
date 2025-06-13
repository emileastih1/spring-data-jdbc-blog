package com.eas.blog.content.infrastructure;

import com.eas.blog.account.domain.Author;
import com.eas.blog.content.domain.Post;
import com.eas.blog.content.dto.PostSimpleView;
import com.eas.blog.content.dto.PostView;
import com.eas.blog.content.dto.PostViewNew;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends ListCrudRepository<Post, Integer> {

    @Query("SELECT * FROM POST WHERE main_author_id = :id")
    List<Post> findByMainAuthorId(Integer id);

    List<Post> findByMainAuthorId(AggregateReference<Author, Integer> authorId);

    List<Post> findAllByMainAuthorId(Integer authorId);

    //List<Post> findByAuthorLastNameIgnoreCase(String lastName);

    @Query("""
            SELECT p.id, p.title, p.content, p.published_on,
                   a.id as main_author_id, a.first_name, a.last_name, a.email, a.username
            FROM post p
            JOIN author a ON p.author = a.id
            """)
    List<PostView> findAllPostsWithAuthorDetails();


    @Query("""
            SELECT p.id, p.title, p.content, p.published_on,
            a.id as main_author_id, a.first_name, a.last_name, a.email, a.username
            FROM post p
            JOIN author a ON p.author = a.id
            """)
    List<PostViewNew> findAllPostsWithAuthorDetailsNew();


    @Query("""
                SELECT p.id, p.title, p.content, p.published_on, p.main_author_id
                FROM Post p
                WHERE p.main_author_id = :authorId
                ORDER BY p.created_on DESC
            """)
    List<PostSimpleView> findSimpleViewsByAuthorOrderedByDateDesc(@Param("authorId") int authorId);

}
