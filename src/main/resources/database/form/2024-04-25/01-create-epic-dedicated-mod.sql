--liquibase formatted sql
--changeset galajus:46
CREATE TABLE epic_dedicated_mod
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    epic_name VARCHAR(255) NULL,
    effect    VARCHAR(255) NULL,
    CONSTRAINT pk_epicdedicatedmod PRIMARY KEY (id)
);

--changeset galajus:47
insert into epic_dedicated_mod (epic_name, effect)
values ('Żmij', 'DOUBLE_HIT_CHANCE'),
       ('Gorthdar', 'EXTRA_FIRE_DAMAGE'),
       ('Attawa', 'MAGICAL_HIT_MODIFIER'),
       ('Imisindo', 'RANGE_HIT_MODIFIER'),
       ('Washi', 'PHYSICAL_HIT_MODIFIER'),
       ('Allenor', 'PHYSICAL_DAMAGE_INCREASE'),
       ('Latarnia Życia', 'MANA_DRAIN');