create table client
(
    id       bigint auto_increment
        primary key,
    name     varchar(50)                                            not null,
    surname  varchar(50)                                            not null,
    login    varchar(50)                                            not null,
    password varchar(50)                                            not null,
    type     enum ('usual', 'corporate', 'regular') default 'usual' not null,
    gender   enum ('male', 'female')                                not null,
    balance  decimal(10, 2)                                         not null,
    state    enum ('active', 'deleted', 'blocked')                  null
);

create table exercise
(
    id   bigint auto_increment
        primary key,
    name varchar(50) not null
);

create table trainer
(
    id            bigint                                not null
        primary key,
    name          varchar(50)                           not null,
    surname       varchar(50)                           not null,
    login         varchar(50)                           not null,
    password      varchar(50)                           not null,
    qualification enum ('junior', 'middle', 'senior')   not null,
    gender        enum ('male', 'female')               not null,
    state         enum ('active', 'deleted', 'blocked') null
);

create table `order`
(
    id           bigint auto_increment
        primary key,
    client_id    bigint                                     not null,
    trainer_id   bigint                                     not null,
    date         date                                       not null,
    trainer_type enum ('personal', 'usual') default 'usual' not null,
    price        decimal(10, 2)                             not null,
    is_paid      tinyint(1)                                 null,
    state        enum ('created', 'processed', 'finished')  null,
    constraint order_ibfk_1
        foreign key (client_id) references client (id),
    constraint order_ibfk_2
        foreign key (trainer_id) references trainer (id)
);

create index client_id
    on `order` (client_id);

create index trainer_id
    on `order` (trainer_id);

create table program
(
    id       bigint auto_increment
        primary key,
    order_id bigint                                                not null,
    start    date                                                  not null,
    end      date                                                  not null,
    has_diet tinyint                                               not null,
    feedback mediumtext                                            null,
    state    enum ('prepared', 'process', 'finished', 'rescinded') null,
    constraint program_ibfk_1
        foreign key (order_id) references `order` (id)
);

create index order_id
    on program (order_id);

create table program_exercise
(
    program_id  bigint null,
    exercise_id bigint null,
    constraint program_exercise_ibfk_1
        foreign key (program_id) references program (id),
    constraint program_exercise_ibfk_2
        foreign key (exercise_id) references exercise (id)
);

create index exercise_id
    on program_exercise (exercise_id);

create index program_id
    on program_exercise (program_id);

