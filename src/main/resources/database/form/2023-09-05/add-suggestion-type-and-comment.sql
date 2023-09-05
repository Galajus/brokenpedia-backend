--liquibase formatted sql
--changeset galajus:35
UPDATE `suggestion` SET `type`='BUG' WHERE `type` = '0';
UPDATE `suggestion` SET `type`='IDEA' WHERE `type` = '1';

--liquibase formatted sql
--changeset galajus:36
ALTER TABLE suggestion
    ADD COLUMN status VARCHAR(255) NOT NULL DEFAULT 'UNREAD' AFTER `type`,
    ADD COLUMN admin_comment VARCHAR(1000) AFTER `suggestion`