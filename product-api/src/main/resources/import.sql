INSERT INTO category (ID, DESCRIPTION) VALUES (1000, 'Comic Books');
INSERT INTO category (ID, DESCRIPTION) VALUES (1001, 'Movies');
INSERT INTO category (ID, DESCRIPTION) VALUES (1002, 'Books');

INSERT INTO supplier (ID, NAME) VALUES (1000, 'Panini Comics');
INSERT INTO supplier (ID, NAME) VALUES (1001, 'Amazon');

INSERT INTO product (ID, NAME, QUANTITYAVAILABLE, FK_SUPPLIER, FK_CATEGORY) VALUES (1000, 'Crise nas Terras Infinitas', 10, 1000, 1000);
INSERT INTO product (ID, NAME, QUANTITYAVAILABLE, FK_SUPPLIER, FK_CATEGORY) VALUES (1001, 'Interstelar', 5, 1001, 1001);
INSERT INTO product (ID, NAME, QUANTITYAVAILABLE, FK_SUPPLIER, FK_CATEGORY) VALUES (1002, 'Harry Potter e a Pedra Filosofal', 3, 1001, 1002);