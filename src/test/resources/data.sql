-- USERS
insert into Users (firstname, lastname, email, username)
values ('Alice', 'Dupont', 'alice@example.com', 'alice'),
       ('Bob', 'Martin', 'bob@example.com', 'bobby'),
       ('Claire', 'Durand', 'claire@example.com', 'claire92');

-- AUTHORS
insert into Author (name, email, bio, profile_picture)
values ('John Doe', 'john@example.com', 'Tech enthusiast and blogger.', 'john.jpg'),
       ('Marie Curie', 'marie@example.com', 'Scientist and educator.', 'marie.jpg');

-- POSTS
insert into Post (version, title, content, published_on, created_on, main_author_id)
values
-- John Doe (main_author_id = 1)
(1, 'Spring Boot Guide', 'Learn Spring Boot from scratch...', current_timestamp, '2023-01-01 10:00:00', 1),

-- Marie Curie (main_author_id = 2)
(1, 'Java Streams Explained', 'Understanding Streams in Java 8+', current_timestamp, '2023-02-01 10:00:00', 2),
(1, 'Java Optional Explained', 'Understanding optional in Java 8 and 9', current_timestamp, '2023-03-01 10:00:00', 2),
(1, 'Java Lambda Explained', 'Understanding lambda functions in Java 8+', current_timestamp, '2023-04-01 10:00:00', 2),
(1, 'React Concepts Explained', 'Understanding React basic concepts', current_timestamp, '2023-05-01 10:00:00', 2),
(1, 'GCP Cloud Explained', 'Understanding GCP in the cloud', current_timestamp, '2023-06-01 10:00:00', 2);

-- POST EDITORS
insert into post_editor (post_id, editor_id) values (2, 1), (3, 1), (1, 2);

-- COMMENTS
insert into Comment (post_id, user_id, content)
values (1, 1, 'Great post! Very helpful.'),
       (1, 2, 'Thanks for sharing.'),
       (2, 3, 'Nice explanations.');

-- LIKES
insert into PostLike (post_id, user_id)
values (1, 1),
       (1, 2),
       (2, 3);
