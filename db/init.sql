


-- Crear usuario si no existe
DO $$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles WHERE rolname = 'usuario1'
   ) THEN
      CREATE ROLE usuario1 WITH LOGIN PASSWORD 'clave123';
   END IF;
END
$$;

DO $$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_database WHERE datname = 'practica_db'
   ) THEN
      CREATE DATABASE practica_db OWNER usuario1;
   END IF;
END
$$;

-- Conectarse a la base de datos para ejecutar lo siguiente
\connect practica_db

-- Asegurar permisos sobre el esquema public
GRANT ALL ON SCHEMA public TO usuario1;

-- Crear la tabla solo si no existe
CREATE TABLE IF NOT EXISTS personas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL
);

-- Insertar datos si la tabla está vacía
DO $$
BEGIN
   IF NOT EXISTS (SELECT 1 FROM personas) THEN
      INSERT INTO personas (nombre, apellido) VALUES
         ('Juan', 'Pérez'),
         ('María', 'Gómez');
   END IF;
END
$$;
