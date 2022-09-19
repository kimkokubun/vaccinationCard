CREATE TABLE users
(
    id         serial primary key,
    username   varchar(50) unique  not null,
    password   varchar(255)        not null,
    email      varchar(255) unique not null,
    created_on TIMESTAMP           not null,
    last_login timestamp
);
create table roles
(
    id   serial primary key,
    name varchar(255) unique not null
);

create table user_roles
(
    user_id int,
    role_id int,
    constraint fk_user
        foreign key (user_id) references users (id),
    constraint fk_role
        foreign key (role_id) references roles (id)
)

create table document
(
    id          serial primary key ,
    cpf         varchar(11) unique not null,
    birth_date  date               not null,
    mother_name varchar(255),
    user_id int,
    constraint fk_user
        foreign key (user_id) references users(id)
);


insert into roles(name)
values ('ADM');
insert into roles(name)
values ('USER');
