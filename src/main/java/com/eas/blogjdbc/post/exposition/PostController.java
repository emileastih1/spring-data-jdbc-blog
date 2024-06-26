package com.eas.blogjdbc.post.exposition;

import com.eas.blogjdbc.post.application.dto.PostDetails;
import com.eas.blogjdbc.post.application.dto.PostView;
import com.eas.blogjdbc.post.application.dto.PostViewNew;
import com.eas.blogjdbc.post.domain.Post;
import com.eas.blogjdbc.post.infrastructure.PostRepository;
import com.eas.blogjdbc.user.infrastructure.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostController(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable("id") Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id is not found : "+ id));
    }

    @GetMapping("/{id}/details")
    public PostDetails getPostDetails(@PathVariable("id") Post post) {
        return new PostDetails(post, authorRepository.findById(post.getAuthor().getId()).get());
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable("id") Post post, @RequestBody Post updatedPost) {
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());
        return postRepository.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Post post) {
        postRepository.delete(post);
    }

    @GetMapping("/CustomView")
    public List<PostView> getPostCustomView(){
        return postRepository.findAllPostsWithAuthorDetails();
    }

    @GetMapping("/CustomViewNew")
    public List<PostViewNew> getPostCustomViewNew(){
        return postRepository.findAllPostsWithAuthorDetailsNew();
    }

}
