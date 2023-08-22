--liquibase formatted sql
--changeset galajus:32
CREATE TABLE legendary_item
(
    id                 BIGINT       AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name               VARCHAR(100) NOT NULL UNIQUE,
    type               VARCHAR(50)  NOT NULL,
    weight             INT          NOT NULL,
    `rank`             INT          NOT NULL,
    capacity           INT          NULL,
    `value`            BIGINT       NOT NULL,
    required_level     INT          NULL,
    required_strength  INT          NULL,
    required_dexterity INT          NULL,
    required_power     INT          NULL,
    required_knowledge INT          NULL,
    damage             INT          NULL,
    damage_type        VARCHAR(50)  NULL,
    strength           INT          NULL,
    dexterity          INT          NULL,
    power              INT          NULL,
    knowledge          INT          NULL,
    health             INT          NULL,
    mana               INT          NULL,
    stamina            INT          NULL,
    armor_slashing     INT          NULL,
    armor_crushing     INT          NULL,
    armor_piercing     INT          NULL,
    mental_resistance  INT          NULL,
    fire_resistance    INT          NULL,
    energy_resistance  INT          NULL,
    cold_resistance    INT          NULL
);
--changeset galajus:33
CREATE TABLE legendary_item_monster
(
    legendary_item_id BIGINT NOT NULL,
    monster_id        BIGINT NOT NULL
);

ALTER TABLE legendary_item_monster
    ADD CONSTRAINT fk_legendary_item_on_legendary_item FOREIGN KEY (legendary_item_id) REFERENCES legendary_item (id);

ALTER TABLE legendary_item_monster
    ADD CONSTRAINT fk_legendary_itemon_on_monster FOREIGN KEY (monster_id) REFERENCES monster (id);