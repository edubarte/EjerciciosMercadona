CREATE DATABASE Mercadona;


USE Mercadona;


CREATE TABLE PRODUCTOS (
    nombre varchar(30) NOT NULL,
    precio FLOAT NOT NULL,
    cantidad_en_stock INT NOT NULL,
    PRIMARY KEY (nombre)
);

INSERT INTO PRODUCTOS VALUE ('Leche', 0.75, 100),
    ('Pan redondo cristal', 0.50, 200),
    ('Pan 3 barras', 1.15, 200),
    ('Muesli', 1.70, 150),
    ('Empanada pollo y setas', 3.95, 40),
    ('Avena', 1, 300),
    ('Chocolate', 1.30, 150),
    ('Salmón', 5.7, 50);


SELECT * from PRODUCTOS;