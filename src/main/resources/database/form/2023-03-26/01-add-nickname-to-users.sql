--liquibase formatted sql
--changeset galajus:19
ALTER TABLE users ADD nickname varchar(18) UNIQUE;
--changeset galajus:20
UPDATE users u SET u.nickname = CONCAT('user_', LEFT(UUID(), 8)) where nickname is null;
--changeset galajus:21
ALTER TABLE users MODIFY nickname varchar(18) UNIQUE NOT NULL;