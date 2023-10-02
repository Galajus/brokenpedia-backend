--liquibase formatted sql
--changeset galajus:37
ALTER TABLE legendary_item
    ADD COLUMN family VARCHAR(255) NOT NULL DEFAULT 'RAR' AFTER `name`