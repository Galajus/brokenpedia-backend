--liquibase formatted sql
--changeset galajus:30
CREATE TABLE post_viewer
(
    id        BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    viewer_ip VARCHAR(200)          NOT NULL,
    post_id   BIGINT                NOT NULL
);