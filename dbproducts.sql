CREATE DATABASE IF NOT EXISTS dbproducts CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES ON dbproducts.* TO 'prodadmin'@'localhost' IDENTIFIED BY '78910';
USE dbproducts;

DROP TABLE products;
CREATE TABLE IF NOT EXISTS products (id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY,
prodcode VARCHAR( 50 ) NOT NULL UNIQUE, 
prodname VARCHAR(50) NOT NULL,
price FLOAT( 10 ) DEFAULT 0,
vat FLOAT( 10 ) DEFAULT 24,
description VARCHAR( 200 ) NOT NULL
);

ALTER TABLE products AUTO_INCREMENT = 1;

INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('123AB783', 'HP Notebook 12', 420, 120, 'Laptop PC');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('143AC551', 'HP Notebook 17', 440, 140, 'Laptop PC');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('150BD348', 'HP Desktop PC', 320, 80, 'Desktop PC');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('155765EF', 'HP Desktop PC', 390, 95, 'Desktop PC AMD');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('175881AC', 'Xiaomi Mi A2', 180, 44.5, 'Xiaomi Smartphone');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('185891AD', 'Samsung Galaxy S8', 920, 220, 'Samsung Smartphone');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('193993AT', 'Samsung Galaxy S9', 1020, 260, 'Samsung Smartphone');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('195A93AW', 'iPhone X', 1420, 360, 'Apple Smartphone');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('173A99AW', 'HP 4K TV', 1745.8, 443.5, 'Smart TV');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('193A93A1', 'iPhone XS', 1550, 460, 'Apple Smartphone');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('200123BT', 'Lenovo Tablet', 110, 26, 'Tablet');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('200S23OW', 'Apple Watch Series 3', 700, 180, 'Apple Watch');
INSERT INTO products(prodcode,prodname,price,vat,description) VALUES ('220WE22D ', 'Huawei P20 Pro', 270, 68, 'Huawei Smartphone');




