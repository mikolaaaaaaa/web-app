create database fitness_center;
use fitness_center;

create table `user` (
id int primary key auto_increment,
name varchar(50) not null,
surname varchar(50) not null,
login varchar(50) not null,
password varchar(50) not null,
user_is enum('trainer','client') default 'client',
user_type enum('usual','corporate','regular') default 'usual'
);

create table `order` (
id int primary key auto_increment,
user_id int not null,
date date not null,
trainer_type enum('personal','usual') default 'usual',
foreign key (user_id) references `user`(id)
);

create table `program`(
id int primary key auto_increment,
order_id int not null,
start date not null,
end date not null, 
exercise_type enum('easy','medium','hard') not null default 'easy',
food_type enum('low-calorie diet','high-calorie diet','usual diet') not null default 'usual diet',
price decimal(10,2) not null,
foreign key (order_id) references `order`(id)
);

create table `feedback`(
id int primary key auto_increment,
message text not null,
order_id int not null,
foreign key (order_id) references `order`(id)
);
