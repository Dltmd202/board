create table user(
    id bigint auto_increment primary key,
    email varchar(255) unique not null,
    password varchar(255) not null
);

create table post(
    id bigint auto_increment primary key,
    title varchar(255) not null,
    content varchar(255) not null,
    user_id bigint not null,
    foreign key (user_id) references user(id)
);