--liquibase formatted sql
--changeset galajus:28
ALTER TABLE `skill_custom_effect` CHANGE `value` `value` FLOAT(11) NOT NULL;
ALTER TABLE `skill_psycho_effect` CHANGE `value` `value` FLOAT(11) NOT NULL;