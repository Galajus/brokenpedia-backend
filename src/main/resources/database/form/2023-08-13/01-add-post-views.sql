--liquibase formatted sql
--changeset galajus:29
ALTER TABLE post
    ADD COLUMN `views` bigint DEFAULT 0 AFTER `content`;