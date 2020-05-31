create database db_1;
use db_1;
create table user (
  id char(32) primary key ,
  username varchar(300),
  password varchar(300)
);

insert into user (id, username, password)
values (replace(uuid(),'-',''),'username','password');
insert into user (id, username, password)
values (replace(uuid(),'-',''),'username2','password');
insert into user (id, username, password)
values (replace(uuid(),'-',''),'username3','password');
insert into user (id, username, password)
values (replace(uuid(),'-',''),'username4','password');

select database()