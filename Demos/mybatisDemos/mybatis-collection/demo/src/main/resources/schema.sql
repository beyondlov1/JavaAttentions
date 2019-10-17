CREATE TABLE t_order (
id varchar(20) not null primary key,
user_id varchar(20),
amount INTEGER
);

create table t_order_detail(
id varchar(20) not null primary key,
order_id varchar(20),
good_id varchar(20),
amount INTEGER
)