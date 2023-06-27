--liquibase formatted sql
--changeset galajus:25
CREATE TABLE post
(
    id               BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    creation_date    datetime     NOT NULL,
    last_update_date datetime     NULL,
    is_public        BIT(1)       NOT NULL DEFAULT FALSE,
    author_uuid      BINARY(16)   NOT NULL,
    slug             VARCHAR(255) NOT NULL UNIQUE,
    title            VARCHAR(255) NOT NULL,
    description      VARCHAR(255) NOT NULL,
    content          MEDIUMTEXT   NOT NULL,
    image            VARCHAR(255) NULL,
    CONSTRAINT fk_post_on_author_uuid FOREIGN KEY (author_uuid) REFERENCES users (uuid)
);