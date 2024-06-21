package dev.danvega.blogjdbc;

import dev.danvega.blogjdbc.model.Author;
import dev.danvega.blogjdbc.model.Comment;
import dev.danvega.blogjdbc.model.Post;
import dev.danvega.blogjdbc.repository.AuthorRepository;
import dev.danvega.blogjdbc.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner run(PostRepository postRepository, AuthorRepository authorRepository) {
		return args -> {
			Author author = new Author(null, "Dan", "Vega", "danvega@gmail.com", "dvega");
			Integer authorId = authorRepository.save(author).id();
			AggregateReference<Author,Integer> authorReference = AggregateReference.to(authorId);

			Post post = new Post( "Dan's First Post", "This is Dan's First Post",authorReference);
			post.addComment(new Comment( "Dan", "This is a comment"));
			post.addComment(new Comment( "John", "This is another comment"));
			postRepository.save(post);

			Author author1 = new Author(null, "Emile", "Astih", "emileastih1@gmail.com", "eas");
			Integer authorId1 = authorRepository.save(author1).id();
			AggregateReference<Author,Integer> authorReference1 = AggregateReference.to(authorId1);

			Post post1 = new Post( "Emile's First Post", "This is Emile's First Post",authorReference1);
			post1.addComment(new Comment( "Emile", "This is a comment"));
			post1.addComment(new Comment( "Layal", "This is another comment"));
			postRepository.save(post1);

		};
	}

}
