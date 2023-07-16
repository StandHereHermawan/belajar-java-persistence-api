CREATE DATABASE belajar_java_persistence_api;

use belajar_java_persistence_api;

CREATE TABLE customers (
    id VARCHAR(255) not null primary key ,
    name VARCHAR(100) not null
)ENGINE = InnoDB;

SELECT * FROM customers;