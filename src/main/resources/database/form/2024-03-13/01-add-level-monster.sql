--liquibase formatted sql
--changeset galajus:44
ALTER TABLE monster
    ADD COLUMN level INT NOT NULL DEFAULT '0'