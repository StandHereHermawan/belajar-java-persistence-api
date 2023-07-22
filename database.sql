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

delete
from customers
where id = 2;

ALTER TABLE categories
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE categories
    ADD COLUMN updated_at TIMESTAMP;

CREATE TABLE images
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    image       BLOB
) ENGINE InnoDB;

select *
from images;

CREATE TABLE members
(
    id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email       VARCHAR(150) NOT NULL,
    title       VARCHAR(100),
    first_name  VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name   VARCHAR(100)
) ENGINE = InnoDB;

SELECT * FROM members;