CREATE DATABASE belajar_java_persistence_api;

use belajar_java_persistence_api;

CREATE TABLE customers
(
    id   VARCHAR(255) not null primary key,
    name VARCHAR(100) not null
) ENGINE = InnoDB;

SELECT *
FROM customers;

ALTER TABLE customers
    add column primary_email varchar(150);

CREATE TABLE categories
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) not null,
    description VARCHAR(500)
) engine = InnoDB;

SELECT *
FROM categories;

ALTER TABLE customers
    ADD COLUMN age TINYINT;

ALTER TABLE customers
    Add column married BOOLEAN;

ALTER TABLE customers
add column type VARCHAR(50);