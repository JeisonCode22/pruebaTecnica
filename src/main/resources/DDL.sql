CREATE DATABASE franquicia_db;
USE franquicia_db;

CREATE TABLE franquicia (
  franquicia_id uuid NOT NULL,
  nombre varchar(100) NOT NULL,
  PRIMARY KEY (franquicia_id),
  UNIQUE KEY unique_nombre (nombre)
)

CREATE TABLE sucursal (
  sucursal_id uuid NOT NULL,
  nombre varchar(100) NOT NULL,
  franquicia_id uuid NOT NULL,
  PRIMARY KEY (sucursal_id),
  UNIQUE KEY unique_nombre (nombre),
  KEY franquicia_id (franquicia_id),
  CONSTRAINT sucursal_ibfk_1 FOREIGN KEY (franquicia_id) REFERENCES franquicia (franquicia_id) ON DELETE CASCADE
)

CREATE TABLE producto (
  producto_id uuid NOT NULL,
  nombre varchar(100) NOT NULL,
  stock int(11) NOT NULL DEFAULT 0,
  sucursal_id uuid NOT NULL,
  PRIMARY KEY (producto_id),
  UNIQUE KEY unique_nombre (nombre),
  KEY sucursal_id (sucursal_id),
  CONSTRAINT producto_ibfk_1 FOREIGN KEY (sucursal_id) REFERENCES sucursal (sucursal_id) ON DELETE CASCADE
)