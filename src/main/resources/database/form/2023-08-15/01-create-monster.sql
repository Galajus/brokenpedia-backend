--liquibase formatted sql
--changeset galajus:31
CREATE TABLE monster
(
    id   BIGINT                AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(255)          NOT NULL UNIQUE,
    type VARCHAR(255)          NOT NULL
);