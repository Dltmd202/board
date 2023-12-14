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
    post_id varchar(255) not null unique,
    created_at datetime  not null,
    foreign key (user_id) references user(id)
);

create table comment(
    id bigint auto_increment primary key,
    content varchar(255) not null,
    user_id bigint not null,
    post_id bigint not null,
    deleted_at datetime,
    created_at datetime  not null,
    foreign key (user_id) references user(id),
    foreign key (post_id) references post(id)
);

create table tag(
    name varchar(20) not null primary key
);

create table post_tag(
    tag_id  varchar(20) not null,
    post_id bigint      not null,
    primary key (post_id, tag_id),
    constraint foreign key (tag_id) references tag (name),
    constraint foreign key (post_id) references post (id)
);

create table preference(
    post_id bigint not null,
    user_id bigint not null,
    type varchar(20) not null,
    primary key (post_id, type, user_id),
    constraint foreign key (post_id) references post (id),
    constraint foreign key (user_id) references user (id),
    constraint check (type in ('LIKE', 'UNLIKE'))
)
    Add post preference related sql ddlAdd post preference related sql ddl