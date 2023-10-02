--liquibase formatted sql
--changeset galajus:38
CREATE TABLE orb
(
    id          BIGINT                AUTO_INCREMENT NOT NULL PRIMARY KEY,
    effect      VARCHAR(255)          NOT NULL,
    type        VARCHAR(100)          NOT NULL,
    start_bonus DOUBLE                NOT NULL
);