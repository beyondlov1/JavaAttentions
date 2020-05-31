

create table user(
id char(50) primary key,
username varchar(20) not null,
password varchar(20) not null
);

create table book(
id char(50) primary key,
name varchar(100),
price double,
ownerId char(50),
authorId char(50)
);

create table author(
id char(50) primary key,
name varchar(100)
);