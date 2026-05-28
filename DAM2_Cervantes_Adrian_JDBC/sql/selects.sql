-- DAO Agencia
SELECT id, nombre, pais, fecha_fundacion, autor_examen
FROM agencias
WHERE id = ?;

SELECT id, nombre, pais, fecha_fundacion, autor_examen
FROM agencias
ORDER BY id;

-- DAO Satelite con relacion 1:N hacia Agencia
SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo,
       s.fecha_lanzamiento, s.autor_examen AS s_autor,
       a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion,
       a.autor_examen AS a_autor
FROM satelites s
INNER JOIN agencias a ON s.agencia_id = a.id
WHERE s.id = ?;

SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo,
       s.fecha_lanzamiento, s.autor_examen AS s_autor,
       a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion,
       a.autor_examen AS a_autor
FROM satelites s
INNER JOIN agencias a ON s.agencia_id = a.id
ORDER BY s.id;

SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo,
       s.fecha_lanzamiento, s.autor_examen AS s_autor,
       a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion,
       a.autor_examen AS a_autor
FROM satelites s
INNER JOIN agencias a ON s.agencia_id = a.id
WHERE a.id = ?
ORDER BY s.id;

-- FIND SATELLITE WITH DETAIL: relacion 1:1
SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo,
       s.fecha_lanzamiento, s.autor_examen AS s_autor,
       a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion,
       a.autor_examen AS a_autor,
       d.id AS d_id, d.velocidad_maxima, d.combustible, d.vida_util,
       d.temperatura_maxima, d.autor_examen AS d_autor
FROM satelites s
INNER JOIN agencias a ON s.agencia_id = a.id
INNER JOIN detalle_satelite d ON d.satelite_id = s.id
WHERE s.id = ?;

-- BONUS_QUERY_ADVANCED: satelites activos con Agencia y DetalleSatelite
SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo,
       s.fecha_lanzamiento, s.autor_examen AS s_autor,
       a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion,
       a.autor_examen AS a_autor,
       d.id AS d_id, d.velocidad_maxima, d.combustible, d.vida_util,
       d.temperatura_maxima, d.autor_examen AS d_autor
FROM satelites s
INNER JOIN agencias a ON s.agencia_id = a.id
INNER JOIN detalle_satelite d ON d.satelite_id = s.id
WHERE s.activo = TRUE
ORDER BY s.id;

-- DYNAMIC_UPDATE_ENGINE
-- Ejemplo generado desde AbstractDAO:
UPDATE satelites
SET nombre = ?, coste = ?, activo = ?
WHERE id = ?;
