
create table user(
id char(50),
username varchar(30),
password varchar(50)
);

create table bookforsql(
id char(50),
name varchar(50),
description varchar(300),
author varchar(50),
wordCount int(10),
price double(10,2),
ownerId char(50),
categoryId char(50),
fileUri varchar(100),
fileName varchar(50),
createDate timestamp,
modifyDate timestamp
);

create table categoryforsql(
id char(50),
name varchar(50),
description varchar(300)
);
