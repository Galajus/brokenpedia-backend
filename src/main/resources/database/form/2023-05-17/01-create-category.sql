--liquibase formatted sql
--changeset galajus:23
CREATE TABLE category
(
    id            BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    category_name VARCHAR(60) NOT NULL,
    category_slug VARCHAR(60) NOT NULL
);
--changeset galajus:24
insert into category (category_name, category_slug) values
('News', 'news')
