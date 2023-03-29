--liquibase formatted sql
--changeset galajus:22
ALTER TABLE build
    ADD CONSTRAINT FK_BUILD_ON_OWNER_UUID FOREIGN KEY (owner_uuid) REFERENCES users (uuid);