create table user(
    id bigint auto_increment primary key,
    email varchar(255) unique not null,
    password varchar(255) not null
);