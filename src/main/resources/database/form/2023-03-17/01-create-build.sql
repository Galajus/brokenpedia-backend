--liquibase formatted sql
--changeset galajus:15
CREATE TABLE build_details
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    profession VARCHAR(255)          NULL,
    level      INT                   NULL
);
--changeset galajus:16
CREATE TABLE build
(
    id                BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    owner_uuid        BINARY(16)            NULL,
    build_name        VARCHAR(30)           NULL,
    short_description VARCHAR(150)          NULL,
    `description`     TEXT                  NULL,
    hidden            BOOLEAN DEFAULT FALSE,
    pvp_build         BOOLEAN DEFAULT FALSE,
    build_details_id  BIGINT                NULL,
    CONSTRAINT fk_build_on_build_details FOREIGN KEY (build_details_id) REFERENCES build_details (id)
);
--changeset galajus:17
CREATE TABLE build_liker
(
    id         BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    liker_uuid BINARY(16)            NOT NULL,
    build_id   BIGINT                NOT NULL,
    CONSTRAINT fk_build_liker_on_build_id FOREIGN KEY (build_id) REFERENCES build (id)
);

--changeset galajus:18
CREATE TABLE build_skill_stat_data
(
    id               BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    skill_stat_type  VARCHAR(255)          NULL,
    skill_stat_id    BIGINT                NULL,
    level            INT                   NULL,
    build_details_id BIGINT                NULL,
    CONSTRAINT fk_build_skill_stat_data_on_build_details FOREIGN KEY (build_details_id) REFERENCES build_details (id)
);
