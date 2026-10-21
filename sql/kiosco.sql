-- =====================================================================
--  TP JDBC - Sistema de Kiosco
--  Script de creación de la base de datos y carga de datos iniciales.
--
--  Ejecutalo completo en MySQL Workbench, phpMyAdmin o la consola de
--  MySQL. ATENCIÓN: si la base "kiosco" ya existe, se borra y se vuelve
--  a crear (sirve para "resetear" los datos cuando quieras).
-- =====================================================================

DROP DATABASE IF EXISTS kiosco;
CREATE DATABASE kiosco CHARACTER SET utf8mb4;
USE kiosco;

CREATE TABLE productos (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nombre    VARCHAR(100)  NOT NULL,
    categoria VARCHAR(50)   NOT NULL,
    precio    DECIMAL(10,2) NOT NULL,
    stock     INT           NOT NULL DEFAULT 0
);

INSERT INTO productos (nombre, categoria, precio, stock) VALUES
    ('Alfajor de chocolate',  'Golosinas', 1200.00, 40),
    ('Chicle de menta',       'Golosinas',  500.00, 100),
    ('Agua mineral 500 ml',   'Bebidas',    900.00, 30),
    ('Gaseosa cola 1,5 L',    'Bebidas',   2800.00, 24),
    ('Jugo de naranja 1 L',   'Bebidas',   1900.00, 12),
    ('Papas fritas 150 g',    'Snacks',    2500.00, 18),
    ('Maní salado 200 g',     'Snacks',    1700.00, 15),
    ('Galletitas de agua',    'Almacén',   1100.00, 25),
    ('Yerba mate 1 kg',       'Almacén',   4500.00, 10),
    ('Pilas AA x4',           'Varios',    3600.00, 8);

-- Comprobación rápida: debería mostrar 10 productos
SELECT * FROM productos;
