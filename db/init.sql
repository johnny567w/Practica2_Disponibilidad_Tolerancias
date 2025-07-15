-- Crear tabla banco
CREATE TABLE IF NOT EXISTS banco (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255)
);

-- Crear tabla cliente con FK a banco
CREATE TABLE IF NOT EXISTS cliente (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    correo VARCHAR(255),
    banco_id BIGINT,
    CONSTRAINT fk_cliente_banco FOREIGN KEY (banco_id) REFERENCES banco(id)
);

-- Crear tabla cuenta con FK a cliente
CREATE TABLE IF NOT EXISTS cuenta (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(255),
    saldo DOUBLE PRECISION,
    cliente_id BIGINT,
    CONSTRAINT fk_cuenta_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Crear tabla transaccion con FKs a cuenta (origen y destino)
CREATE TABLE IF NOT EXISTS transaccion (
    id BIGSERIAL PRIMARY KEY,
    tipo VARCHAR(255),
    monto DOUBLE PRECISION,
    fecha TIMESTAMP,
    cuenta_origen_id BIGINT,
    cuenta_destino_id BIGINT,
    CONSTRAINT fk_transaccion_origen FOREIGN KEY (cuenta_origen_id) REFERENCES cuenta(id),
    CONSTRAINT fk_transaccion_destino FOREIGN KEY (cuenta_destino_id) REFERENCES cuenta(id)
);
