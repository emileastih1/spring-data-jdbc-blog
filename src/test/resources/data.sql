insert into Users (firstname, lastname, email, username)
values ('Alice', 'Dupont', 'alice@example.com', 'alice'),
       ('Bob', 'Martin', 'bob@example.com', 'bobby'),
       ('Claire', 'Durand', 'claire@example.com', 'claire92');

insert into Author (name, email, bio, profile_picture)
values ('John Doe', 'john@example.com', 'Tech enthusiast and blogger.', 'john.jpg'),
       ('Marie Curie', 'marie@example.com', 'Scientist and educator.', 'marie.jpg');

insert into Post (version, title, content, published_on, author)
values (1, 'Spring Boot Guide', 'Learn Spring Boot from scratch...', current_timestamp, 1),
       (1, 'Java Streams Explained', 'Understanding Streams in Java 8+', current_timestamp, 2);

insert into Comment (post_id, user_id, content)
values (1, 1, 'Great post! Very helpful.'),
       (1, 2, 'Thanks for sharing.'),
       (2, 3, 'Nice explanations.');

insert into PostLike (post_id, user_id)
values (1, 1),
       (1, 2),
       (2, 3);
