create table user(
    id bigint auto_increment primary key,
    email varchar(255) unique not null,
    password varchar(255) not null,
    index idx_email (email)
);

create table post(
    id bigint auto_increment primary key,
    title varchar(255) not null,
    content varchar(255) not null,
    user_id bigint not null,
    post_id varchar(255) not null unique,
    created_at datetime  not null,
    foreign key fk_post_user (user_id) references user(id),
    index idx_post_id (post_id)
);

create table comment(
    id bigint auto_increment primary key,
    content varchar(255) not null,
    user_id bigint not null,
    post_id bigint not null,
    deleted_at datetime,
    created_at datetime  not null,
    foreign key (user_id) references user(id),
    foreign key (post_id) references post(id),
    index idx_post_id_not_deleted (post_id, deleted_at)
);

create table tag(
    name varchar(20) primary key
);

create table post_tag(
    tag_id  varchar(20) not null,
    post_id bigint not null,
    primary key (post_id, tag_id),
    constraint foreign key (tag_id) references tag (name),
    constraint foreign key (post_id) references post (id)
);

create table preference(
    id bigint auto_increment primary key,
    user_id bigint not null,
    post_id varchar(255) not null,
    type varchar(20) not null,
    deleted_at datetime,
    constraint foreign key (post_id) references post (post_id),
    constraint foreign key (user_id) references user (id),
    constraint check (type in ('LIKE', 'UNLIKE')),
    constraint unique (post_id, type, user_id),
    index idx_post_id_and_type_not_deleted (post_id, type, deleted_at)
);

create table post_view(
    post_id bigint not null,
    user_id bigint not null,
    created_at date not null,
    primary key (post_id, user_id, created_at),
    constraint foreign key (post_id) references post (id),
    constraint foreign key (user_id) references user (id)
);