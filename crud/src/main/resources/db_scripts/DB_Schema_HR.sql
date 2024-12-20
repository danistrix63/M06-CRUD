-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS empresa;

-- Usar la base de datos
USE empresa;

-- Crear la tabla 'departamentos'
CREATE TABLE IF NOT EXISTS departamentos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_departamento VARCHAR(100) NOT NULL,
    id_gerente INT,
    FOREIGN KEY (id_gerente) REFERENCES empleados(id)
) ENGINE=InnoDB;

-- Crear la tabla 'tascas'
CREATE TABLE IF NOT EXISTS tascas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT NOT NULL,
    id_departamento INT,
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id)
) ENGINE=InnoDB;

-- Crear la tabla 'empleados'
CREATE TABLE IF NOT EXISTS empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom_empleat VARCHAR(100) NOT NULL,
    cognom_empleat VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    id_departamento INT,
    FOREIGN KEY (id_departamento) REFERENCES departamentos(id),
    FOREIGN KEY (id_gerente) REFERENCES empleados(id)
) ENGINE=InnoDB;

-- Crear la tabla 'historico' para almacenar registros históricos de tareas
CREATE TABLE IF NOT EXISTS historico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_tasca INT,
    fecha DATE,
    descripcion TEXT,
    FOREIGN KEY (id_tasca) REFERENCES tascas(id)
) ENGINE=InnoDB;

-- Crear la tabla 'jobs' para definir trabajos (esto podría ser útil si tienes más información relacionada con los trabajos)
CREATE TABLE IF NOT EXISTS jobs (
    job_id VARCHAR(10) PRIMARY KEY,
    job_title VARCHAR(100),
    min_salary DECIMAL(10, 2),
    max_salary DECIMAL(10, 2)
) ENGINE=InnoDB;
