--liquibase formatted sql
--changeset galajus:39
CREATE TABLE drif
(
    id                   BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    short_name           VARCHAR(10)           NOT NULL,
    start_power          DOUBLE                NOT NULL,
    psycho_grow_by_level DOUBLE                NOT NULL,
    category             VARCHAR(255)          NOT NULL,
    psycho_mod           VARCHAR(255)          NOT NULL
);