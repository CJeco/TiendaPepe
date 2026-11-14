CREATE DATABASE IF NOT EXISTS db_tienda_pepe;
USE db_tienda_pepe;

DROP TABLE IF EXISTS marca;

CREATE TABLE marca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    estado INT DEFAULT 1, -- 1: Activo, 0: Inactivo
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Inserción de datos iniciales para Marcos comunes en bodegas
INSERT INTO marca (nombre, descripcion, estado) VALUES
('Gloria', 'Productos lácteos y derivados', 1),
('Costeño', 'Arroz, granos y abarrotes', 1),
('Nestlé', 'Alimentos y bebidas', 1),
('San Fernando', 'Embutidos y carnes', 1),
('Bimbo', 'Panadería y snacks', 1);

DROP TABLE IF EXISTS detalle_venta;
DROP TABLE IF EXISTS venta;

CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subtotal DECIMAL(10,2) NOT NULL,
    descuento DECIMAL(10,2) DEFAULT 0.00,
    subtotal_con_descuento DECIMAL(10,2) NOT NULL,
    igv DECIMAL(10,2) NOT NULL,
    comision_tarjeta DECIMAL(10,2) DEFAULT 0.00,
    total_final DECIMAL(10,2) NOT NULL,
    metodo_pago VARCHAR(20) NOT NULL, -- 'EFECTIVO' o 'TARJETA'
    fecha_venta TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    producto VARCHAR(150) NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10,2) NOT NULL,
    subtotal_item DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES venta(id) ON DELETE CASCADE
);