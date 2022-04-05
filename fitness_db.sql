use third_fc;

create table if not exists `client`
(
    id       bigint primary key auto_increment,
    name     varchar(50)                          not null,
    surname  varchar(50)                          not null,
    login    varchar(50)                          not null,
    password varchar(50)                          not null,
    type     enum ('usual','corporate','regular') not null default 'usual',
    gender   enum ('male','female')               not null
);

create table if not exists `trainer`
(
    id            bigint primary key                not null,
    name          varchar(50)                       not null,
    surname       varchar(50)                       not null,
    login         varchar(50)                       not null,
    password      varchar(50)                       not null,
    qualification enum ('junior','middle','senior') not null,
    gender        enum ('male','female')            not null
);

create table if not exists `order`
(
    id           bigint primary key auto_increment,
    client_id    bigint                    not null,
    trainer_id   bigint                    not null,
    date         date                      not null,
    trainer_type enum ('personal','usual') not null default 'usual',
    foreign key (client_id) references `client` (id),
    foreign key (trainer_id) references `trainer` (id)
);

create table if not exists `program`
(
    id       bigint primary key auto_increment,
    order_id bigint  not null,
    start    date    not null,
    end      date    not null,
    has_diet tinyint not null,
    feedback mediumtext,
    foreign key (order_id) references `order` (id)
);

create table if not exists `exercise`
(
    id   bigint primary key auto_increment,
    name varchar(50) not null
);

create table if not exists `program_exercise`
(
    program_id  bigint,
    exercise_id bigint,
    foreign key (program_id) references `program` (id),
    foreign key (exercise_id) references `exercise` (id)
);
