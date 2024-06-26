-- noinspection SqlNoDataSourceInspectionForFile
--users
create table AppUser (
    id int auto_increment primary key,
    firstname varchar(100) not null,
    lastname varchar(100) not null,
    email varchar(255) not null unique,
    username varchar(100) not null unique,
    bio text,
    profile_picture varchar(255),
    user_type int default 0, -- 0 for user 1 for author
    created_on timestamp not null default current_timestamp
);

-- post
create table Post (
    id int auto_increment primary key ,
    version int,
    title varchar(255) not null,
    content text not null,
    published_on timestamp not null,
    updated_on timestamp,
    author int,
    foreign key (author) references AppUser(id)
);

-- comment
create table Comment(
    id int auto_increment primary key,
    post_id int not null,
    user_id int not null,
    content text not null,
    published_on timestamp not null default current_timestamp,
    updated_on timestamp default current_timestamp on update current_timestamp,
    foreign key (post_id) references Post(id),
    foreign key (user_id) references AppUser(id)
);

--Like
create table PostLike(
    id int auto_increment primary key,
    post_id int not null,
    user_id int not null,
    liked_on timestamp not null default current_timestamp,
    foreign key (post_id) references Post(id),
    foreign key (user_id) references AppUser(id)
);
