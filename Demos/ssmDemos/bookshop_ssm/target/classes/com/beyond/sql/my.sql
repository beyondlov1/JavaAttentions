create table user(
id char(50) primary key,
username varchar(20),
password varchar(20)
);

create table book(
id char(50) primary key,
name varchar(20),
price double,
book_uri varchar(255),
cover_uri varchar(255),
author_id char(50),
owner_id char(50),
borrower_id char(50),
book_count int(10)
);

create table user_want_borrow_book(
user_id char(50),
book_id char(50),
create_time timestamp,
exchange_book_id char(50),
owner_permission_status int(5),
owner_confirm_status int(5),
borrower_confirm_status int(5)
);

create table author(
id char(50) primary key,
name varchar(20)
);


