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

SELECT *
FROM members;

CREATE TABLE departments
(
    company_id    VARCHAR(100) NOT NULL,
    department_id VARCHAR(100) NOT NULL,
    name          VARCHAR(150) NOT NULL,
    PRIMARY KEY (company_id, department_id)
) ENGINE InnoDB;

select *
from departments;

CREATE TABLE hobbies
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT          NOT NULL,
    name      VARCHAR(150) NOT NULL,
    FOREIGN KEY fk_members_hobbies (member_id)
        REFERENCES members (id)
) ENGINE = InnoDB;

SELECT *
FROM hobbies;

CREATE TABLE skills
(
    id        INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    member_id INT          NOT NULL,
    name      VARCHAR(100) NOT NULL,
    value     INT          NOT NULL,
    FOREIGN KEY fk_members_skills (member_id) REFERENCES members (id),
    CONSTRAINT skills_unique UNIQUE (member_id, name)
) ENGINE = InnoDB;

SELECT *
FROM skills;

CREATE TABLE credentials
(
    id       VARCHAR(100) NOT NULL PRIMARY KEY,
    email    VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL
) ENGINE = InnoDB;

SELECT *
FROM credentials;

CREATE TABLE users
(
    id   VARCHAR(100) NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL
) ENGINE = InnoDB;

SELECT *
FROM users;

CREATE TABLE wallet
(
    id      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(100) NOT NULL,
    balance BIGINT       NOT NULL,
    FOREIGN KEY fk_users_wallet (user_id) REFERENCES users (id)
) ENGINE = InnoDB;

SELECT *
FROM wallet;

CREATE TABLE brands
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description VARCHAR(500)
) ENGINE = InnoDB;

ALTER TABLE brands
    ADD COLUMN created_at TIMESTAMP;

ALTER TABLE brands
    ADD COLUMN updated_at TIMESTAMP;

ALTER TABLE brands
    ADD COLUMN version BIGINT;

SELECT *
FROM brands;

CREATE TABLE products
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    brand_id    VARCHAR(100) NOT NULL,
    name        VARCHAR(100) NOT NULL,
    price       BIGINT       NOT NULL,
    description VARCHAR(500),
    FOREIGN KEY fk_brands_products (brand_id) REFERENCES brands (id)
) ENGINE = InnoDB;

INSERT INTO products (id, brand_id, name, price, description)
    VALUE ('p3','xiaomi','Xiaomi 1',1499000,'Hp Entry Level');

INSERT INTO products (id, brand_id, name, price, description)
    VALUE ('p4','xiaomi','Xiaomi 2',3599000,'Hp Gaming Mainstream');

INSERT INTO products (id, brand_id, name, price, description)
    VALUE ('p5','xiaomi','Xiaomi 3',11499000,'Hp High-end');

SELECT *
FROM products;

CREATE TABLE users_like_products
(
    user_id    VARCHAR(100) NOT NULL,
    product_id VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_users_to_users_like_products (user_id) REFERENCES users (id),
    FOREIGN KEY fk_products_to_users_like_products (product_id) REFERENCES products (id),
    PRIMARY KEY (user_id, product_id)
) ENGINE = InnoDB;

SELECT *
FROM users_like_products;

CREATE TABLE employees
(
    id             VARCHAR(100) NOT NULL PRIMARY KEY,
    type           VARCHAR(50)  NOT NULL,
    name           VARCHAR(100) NOT NULL,
    total_manager  INT,
    total_employee INT
) ENGINE = InnoDB;

SELECT *
FROM employees;

CREATE TABLE payments
(
    id     VARCHAR(100) NOT NULL PRIMARY KEY,
    amount BIGINT       NOT NULL
) ENGINE = InnoDB;

SELECT *
FROM payments;

CREATE TABLE payments_gopay
(
    id       VARCHAR(100) NOT NULL PRIMARY KEY,
    gopay_id VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_payments_gopay (id) REFERENCES payments (id)
) ENGINE = InnoDB;

SELECT *
FROM payments_gopay;

CREATE TABLE payments_credit_card
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    masked_card VARCHAR(100) NOT NULL,
    bank        VARCHAR(100) NOT NULL,
    FOREIGN KEY fk_payments_credit_card (id) REFERENCES payments (id)
) ENGINE = InnoDB;

SELECT *
FROM payments_credit_card;

CREATE TABLE transactions
(
    id         VARCHAR(100) NOT NULL PRIMARY KEY,
    balance    BIGINT       NOT NULL,
    created_at TIMESTAMP    NOT NULL
) ENGINE = InnoDB;

SELECT *
FROM transactions;

CREATE TABLE transactions_credit
(
    id            VARCHAR(100) NOT NULL PRIMARY KEY,
    balance       BIGINT       NOT NULL,
    created_at    TIMESTAMP    NOT NULL,
    credit_amount BIGINT       NOT NULL
) ENGINE = InnoDB;

SELECT *
FROM transactions_credit;

CREATE TABLE transactions_debit
(
    id           VARCHAR(100) NOT NULL PRIMARY KEY,
    balance      BIGINT       NOT NULL,
    created_at   TIMESTAMP    NOT NULL,
    debit_amount BIGINT       NOT NULL
) ENGINE = InnoDB;

SELECT *
FROM transactions_debit;