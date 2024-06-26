package com.eas.blogjdbc;

import com.eas.blogjdbc.post.domain.Comment;
import com.eas.blogjdbc.post.domain.Like;
import com.eas.blogjdbc.post.domain.Post;
import com.eas.blogjdbc.post.infrastructure.PostRepository;
import com.eas.blogjdbc.user.domain.Author;
import com.eas.blogjdbc.user.domain.User;
import com.eas.blogjdbc.user.domain.UserType;
import com.eas.blogjdbc.user.infrastructure.AuthorRepository;
import com.eas.blogjdbc.user.infrastructure.UserRepository;
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
	CommandLineRunner run(PostRepository postRepository, AuthorRepository authorRepository, UserRepository userRepository) {
		return args -> {
			Author author = new Author("Dan", "Vega", "danvega@gmail.com", "dvega", "Dan Vega's picture");
			Integer authorId = authorRepository.save(author).getId();
			AggregateReference<Author,Integer> authorReference = AggregateReference.to(authorId);

			Post post = new Post( "Dan's First Post", "This is Dan's First Post",authorReference);

			User user1= new User("userComment1", "userComment1", "userComment1@gmail.com",
					"userComment1username", UserType.USER.value, "");
			Integer user1Id = userRepository.save(user1).getId();

			post.addComment(new Comment( "This is a comment",user1Id));
			post.addComment(new Comment( "This is another comment", user1Id));


			User user2 = new User("like1", "like1", "like1@gmail.com", "like1username",
					UserType.USER.value, "like1's picture");
			Integer user2Id = userRepository.save(user2).getId();
			post.like(new Like( user2Id));

			postRepository.save(post);
			/*
			Author author1 = new Author(null, "Emile", "Astih", "emileastih1@gmail.com", "eas");
			Integer authorId1 = authorRepository.save(author1).getId();
			AggregateReference<Author,Integer> authorReference1 = AggregateReference.to(authorId1);

			Post post1 = new Post( "Emile's First Post", "This is Emile's First Post",authorReference1);
			post1.addComment(new Comment( "Emile", "This is a comment"));
			post1.addComment(new Comment( "Layal", "This is another comment"));
			postRepository.save(post1);*/

		};

	}


}
