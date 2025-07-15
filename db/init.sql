DROP TABLE IF EXISTS transaccion;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS banco;

CREATE TABLE banco (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    banco_id INTEGER REFERENCES banco(id)
);

CREATE TABLE cuenta (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    saldo NUMERIC(12,2) NOT NULL,
    cliente_id INTEGER REFERENCES cliente(id)
);

CREATE TABLE transaccion (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50),
    monto NUMERIC(12,2),
    fecha TIMESTAMP,
    cuenta_origen_id INTEGER,
    cuenta_destino_id INTEGER
);

INSERT INTO banco (nombre) VALUES
('Banco Pichincha'),
('Banco Guayaquil'),
('Banco Internacional');

INSERT INTO cliente (nombre, correo, banco_id) VALUES
('Ana Torres', 'ana@example.com', 1),
('Luis Pérez', 'luis@example.com', 2),
('Carla Mejía', 'carla@example.com', 3);

INSERT INTO cuenta (tipo, saldo, cliente_id) VALUES
('Ahorro', 1500.00, 1),
('Corriente', 3200.00, 2),
('Ahorro', 500.00, 3);

INSERT INTO transaccion (tipo, monto, fecha, cuenta_origen_id, cuenta_destino_id) VALUES
('Depósito', 500.00, NOW(), NULL, 1),
('Transferencia', 250.00, NOW(), 2, 3),
('Retiro', 100.00, NOW(), 1, NULL);

CREATE DATABASE usuario1;
ALTER TABLE cuenta ADD COLUMN numero VARCHAR(255);
