--liquibase formatted sql
--changeset galajus:8
create table skill_cost(
                                  id bigint not null auto_increment PRIMARY KEY,
                                  level int not null,
                                  single_cost int not null,
                                  sum_cost int not null
);
--changeset galajus:9
insert into skill_cost (id, level, single_cost, sum_cost) values
(1, 0, 0, 0),
(2, 1, 1, 1),
(3, 2, 2, 3),
(4, 3, 3, 6),
(5, 4, 4, 10),
(6, 5, 5, 15),
(7, 6, 6, 21),
(8, 7, 7, 28),
(9, 8, 14, 42),
(10, 9, 28, 70),
(11, 10, 42, 112),
(12, 11, 56, 168),
(13, 12, 70, 238),
(14, 13, 84, 322),
(15, 14, 98, 420),
(16, 15, 196, 616),
(17, 16, 392, 1008),
(18, 17, 588, 1596),
(19, 18, 784, 2380),
(20, 19, 980, 3360),
(21, 20, 1176, 4536),
(22, 21, 1372, 5908)