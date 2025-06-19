drop table if exists PostLike;
drop table if exists Comment;
drop table if exists Post_Editor;
drop table if exists Post;
drop table if exists Author;
drop table if exists Users;

create table Users
(
    id         SERIAL primary key,
    firstname  varchar(100) not null,
    lastname   varchar(100) not null,
    email      varchar(255) not null unique,
    username   varchar(100) not null unique,
    created_on timestamp    not null default current_timestamp
);

create table Author
(
    id              SERIAL primary key,
    name            varchar(100) not null,
    email           varchar(255) not null unique,
    bio             text,
    profile_picture varchar(255),
    created_on      timestamp    not null default current_timestamp
);

create table Post
(
    id             SERIAL primary key,
    version        int,
    title          varchar(255) not null,
    content        text         not null,
    created_on     timestamp    not null default current_timestamp,
    published_on   timestamp,
    updated_on     timestamp,
    main_author_id int,
    foreign key (main_author_id) references Author (id)
);

create table Post_Editor
(
    id        SERIAL primary key,
    post_id   int not null,
    editor_id int not null,
    foreign key (post_id) references Post (id),
    foreign key (editor_id) references Author (id)
);

create table Comment
(
    id           SERIAL primary key,
    post_id      int       not null,
    user_id      int       not null,
    content      text      not null,
    published_on timestamp default current_timestamp,
    updated_on   timestamp          default current_timestamp,
    foreign key (post_id) references Post (id),
    foreign key (user_id) references Users (id)
);

create table PostLike
(
    id       SERIAL primary key,
    post_id  int       not null,
    user_id  int       not null,
    liked_on timestamp not null default current_timestamp,
    foreign key (post_id) references Post (id),
    foreign key (user_id) references Users (id)
);
