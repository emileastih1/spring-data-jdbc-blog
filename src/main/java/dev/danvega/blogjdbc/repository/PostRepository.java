package dev.danvega.blogjdbc.repository;

import dev.danvega.blogjdbc.dto.PostView;
import dev.danvega.blogjdbc.dto.PostViewNew;
import dev.danvega.blogjdbc.model.Post;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Integer> {

    @Query("SELECT * FROM POST WHERE author = :id")
    List<Post> findByAuthor(Integer id);

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
