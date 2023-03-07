--liquibase formatted sql
--changeset galajus:6
create table default_statistic(
                            id bigint not null auto_increment PRIMARY KEY,
                            name varchar(35) not null,
                            image varchar(20) not null,
                            level int not null,
                            min_level int not null
);
--changeset galajus:7
insert into default_statistic (id, name, image, level, min_level) values
    (1, 'Zdrowie', 'health', 20, 20),
    (2, 'Mana', 'mana', 20, 20),
    (3, 'Kondycja', 'stamina', 20, 20),
    (4, 'Siła', 'strength', 10, 10),
    (5, 'Zręczność', 'dexterity', 10, 10),
    (6, 'Moc', 'power', 10, 10),
    (7, 'Wiedza', 'knowledge', 10, 10)