--liquibase formatted sql
--changeset galajus:40
ALTER TABLE legendary_item
    ADD COLUMN item_set_id bigint NULL AFTER `type`