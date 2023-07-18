--liquibase formatted sql
--changeset galajus:27
ALTER TABLE class_skill
    ADD COLUMN `formula`      varchar(100) AFTER `name`,
    ADD COLUMN `requirements` varchar(100) AFTER `formula`;