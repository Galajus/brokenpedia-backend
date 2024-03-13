--liquibase formatted sql
--changeset galajus:41
CREATE TABLE `item_set`
(
    id             BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name           VARCHAR(255)          NULL,
    required_class VARCHAR(255)          NULL
);

--changeset galajus:42
CREATE TABLE item_set_custom_effect
(
    id     BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    effect VARCHAR(255)          NULL,
    value  FLOAT                 NULL,
    item_set_id BIGINT           NULL
);

ALTER TABLE item_set_custom_effect
    ADD CONSTRAINT FK_ITEMSETCUSTOMEFFECT_ON_ITEMSETID FOREIGN KEY (item_set_id) REFERENCES item_set (id);


--changeset galajus:43
CREATE TABLE item_set_psycho_effect
(
    id     BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    effect SMALLINT              NULL,
    value   FLOAT                NULL,
    item_set_id BIGINT           NULL
);

ALTER TABLE item_set_psycho_effect
    ADD CONSTRAINT FK_ITEMSETPSYCHOEFFECT_ON_ITEMSETID FOREIGN KEY (item_set_id) REFERENCES item_set (id);