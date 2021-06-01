create database blog ;
use blog ;
create table user(
    username varchar(20) primary key ,
    password varchar(20) not null ,
    name varchar(20) not null ,
    gender varchar(2) ,
    introduce varchar(255) ,
    status int(11) not null DEFAULT '1',
    regtime timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
create table blog(
    author varchar(20) not null ,
    title varchar(255) not null ,
    string text not null ,
    blogtime timestamp not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
