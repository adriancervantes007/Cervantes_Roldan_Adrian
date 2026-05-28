
PREGUNTA 1

Explica cómo funciona la relación 1:N entre Agencia y Satelite tanto en SQL como en Java.


La relacion 1:N entre Agencia y Satelite significa que una agencia puede tener muchos satelites, pero cada satelite pertenece a una sola agencia.
En Java se representa creando una clase Agencia y una clase Satelite. Dentro de Satelite se guarda un objeto completo

PREGUNTA 2

Explica por qué en Java utilizamos: private Agencia agencia; y no: private int agenciaId;

Usamos private Agencia agencia y no private int agenciaId porque Java debe trabajar con objetos relacionados, no solo con identificadores de base de datos

PREGUNTA 3

Explica qué ventaja aporta PreparedStatement frente a concatenar SQL manualmente.

La ventaja es que separa la consulta SQL de los valores introducidos por el usuario. Esto evita inyeccion SQL, 
porque los parametros se tratan como datos y no como parte del codigo SQL