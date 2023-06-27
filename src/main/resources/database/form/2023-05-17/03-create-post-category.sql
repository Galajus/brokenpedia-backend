--liquibase formatted sql
--changeset galajus:26
CREATE TABLE post_category
(
    category_id BIGINT NOT NULL,
    post_id     BIGINT NOT NULL,
    CONSTRAINT fk_post_category_on_category FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT fk_post_category_on_post FOREIGN KEY (post_id) REFERENCES post (id)
);