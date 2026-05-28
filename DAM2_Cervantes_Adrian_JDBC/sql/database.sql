DROP TABLE IF EXISTS detalle_satelite;
DROP TABLE IF EXISTS satelites;
DROP TABLE IF EXISTS agencias;

CREATE TABLE agencias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    pais VARCHAR(80) NOT NULL,
    fecha_fundacion DATE NOT NULL,
    autor_examen VARCHAR(150) NOT NULL
);

CREATE TABLE satelites (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    orbita VARCHAR(80) NOT NULL,
    peso NUMERIC(10,2) NOT NULL CHECK (peso > 0),
    coste NUMERIC(14,2) NOT NULL CHECK (coste >= 0),
    activo BOOLEAN NOT NULL,
    fecha_lanzamiento DATE NOT NULL,
    agencia_id INTEGER NOT NULL,
    autor_examen VARCHAR(150) NOT NULL,
    CONSTRAINT fk_satelites_agencias
        FOREIGN KEY (agencia_id)
        REFERENCES agencias(id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
);

CREATE TABLE detalle_satelite (
    id SERIAL PRIMARY KEY,
    velocidad_maxima NUMERIC(10,2) NOT NULL CHECK (velocidad_maxima > 0),
    combustible VARCHAR(80) NOT NULL,
    vida_util INTEGER NOT NULL CHECK (vida_util > 0),
    temperatura_maxima NUMERIC(8,2) NOT NULL,
    satelite_id INTEGER NOT NULL UNIQUE,
    autor_examen VARCHAR(150) NOT NULL,
    CONSTRAINT fk_detalle_satelite_satelites
        FOREIGN KEY (satelite_id)
        REFERENCES satelites(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
