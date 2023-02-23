--liquibase formatted sql
--changeset galajus:1
create table users(
                      id bigint not null auto_increment PRIMARY KEY,
                      username varchar(50) not null unique,
                      password varchar(500) not null,
                      enabled boolean not null,
                      confirm_account_hash varchar(120) not null,
                      lost_password_hash varchar(120),
                      lost_password_hash_date datetime
);
--changeset galajus:2
create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);
--changeset galajus:3
create unique index ix_auth_username on authorities (username,authority);