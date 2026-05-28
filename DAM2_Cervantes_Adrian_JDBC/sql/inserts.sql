INSERT INTO agencias (nombre, pais, fecha_fundacion, autor_examen) VALUES
('NASA', 'Estados Unidos', '1958-07-29', 'ADRIAN_CERVANTES_DAM2'),
('ESA', 'Europa', '1975-05-30', 'ADRIAN_CERVANTES_DAM2'),
('JAXA', 'Japon', '2003-10-01', 'ADRIAN_CERVANTES_DAM2'),
('ISRO', 'India', '1969-08-15', 'ADRIAN_CERVANTES_DAM2'),
('CONAE', 'Argentina', '1991-05-28', 'ADRIAN_CERVANTES_DAM2');

INSERT INTO satelites (nombre, orbita, peso, coste, activo, fecha_lanzamiento, agencia_id, autor_examen) VALUES
('Hubble', 'LEO', 11110.00, 2500000000.00, TRUE, '1990-04-24', 1, 'ADRIAN_CERVANTES_DAM2'),
('Sentinel-2A', 'SSO', 1140.00, 230000000.00, TRUE, '2015-06-23', 2, 'ADRIAN_CERVANTES_DAM2'),
('Hayabusa2', 'Heliocentrica', 609.00, 150000000.00, FALSE, '2014-12-03', 3, 'ADRIAN_CERVANTES_DAM2'),
('Chandrayaan-2', 'Lunar', 3850.00, 141000000.00, TRUE, '2019-07-22', 4, 'ADRIAN_CERVANTES_DAM2'),
('SAOCOM 1A', 'LEO', 3000.00, 400000000.00, TRUE, '2018-10-08', 5, 'ADRIAN_CERVANTES_DAM2');

INSERT INTO detalle_satelite (velocidad_maxima, combustible, vida_util, temperatura_maxima, satelite_id, autor_examen) VALUES
(28000.00, 'Hidrazina', 15, 80.00, 1, 'ADRIAN_CERVANTES_DAM2'),
(27000.00, 'Xenon', 7, 65.00, 2, 'ADRIAN_CERVANTES_DAM2'),
(12000.00, 'Ionico', 6, 55.00, 3, 'ADRIAN_CERVANTES_DAM2'),
(10500.00, 'Bipropelente', 8, 70.00, 4, 'ADRIAN_CERVANTES_DAM2'),
(27500.00, 'Monopropelente', 5, 60.00, 5, 'ADRIAN_CERVANTES_DAM2');
