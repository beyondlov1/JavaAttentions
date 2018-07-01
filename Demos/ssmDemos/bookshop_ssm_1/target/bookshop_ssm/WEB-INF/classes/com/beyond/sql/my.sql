create table user (
  id       char(50) primary key,
  username varchar(20),
  password varchar(20)
);

create table book (
  id          char(50) primary key,
  name        varchar(20),
  price       double,
  book_count  int(10),
  book_uri    varchar(255),
  cover_uri   varchar(255),
  author_id   char(50),
  owner_id    char(50),
  borrower_id char(50)
);

create table orderz (
  id               char(50) primary key,
  book_id          char(50),
  owner_id         char(50),
  exchange_book_id char(50),
  exchanger_id     char(50),
  create_time      timestamp,
  status           int(2)
);

create table author (
  id   char(50) primary key,
  name varchar(20)
);


