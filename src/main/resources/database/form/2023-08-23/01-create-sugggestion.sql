--liquibase formatted sql
--changeset galajus:34
CREATE TABLE suggestion
(
    id         BIGINT                AUTO_INCREMENT NOT NULL PRIMARY KEY,
    author     VARCHAR(255)          NOT NULL,
    type       VARCHAR(255)          NOT NULL,
    suggestion TEXT                  NOT NULL
);