--liquibase formatted sql
--changeset galajus:45
ALTER TABLE monster
    ADD COLUMN short_name VARCHAR(10) NOT NULL default 'unnamed'