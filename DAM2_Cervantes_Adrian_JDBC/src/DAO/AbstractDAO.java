package DAO;

import motores.MotorFactory;
import motores.MotorSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.StringJoiner;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected static final String AUTOR_EXAMEN = "ADRIAN_CERVANTES_DAM2";
    protected final MotorSQL motorSQL;

    protected AbstractDAO() {
        this.motorSQL = MotorFactory.create(MotorFactory.POSTGRE);
    }

    protected Connection openConnection() throws SQLException {
        motorSQL.connect();
        return motorSQL.getConnection();
    }

    protected void closeConnection() {
        motorSQL.disconnect();
    }

    protected int executeDynamicUpdate(String tableName, String idColumn, int id, Map<String, Object> fields)
            throws SQLException {
        if (fields == null || fields.isEmpty()) {
            throw new IllegalArgumentException("No hay campos para actualizar");
        }

        validateSqlIdentifier(tableName);
        validateSqlIdentifier(idColumn);

        StringJoiner setJoiner = new StringJoiner(", ");
        for (String field : fields.keySet()) {
            validateSqlIdentifier(field);
            setJoiner.add(field + " = ?");
        }

        String sql = "UPDATE " + tableName + " SET " + setJoiner + " WHERE " + idColumn + " = ?";

        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            int index = 1;
            for (Object value : fields.values()) {
                ps.setObject(index++, value);
            }
            ps.setInt(index, id);
            return ps.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    private void validateSqlIdentifier(String identifier) {
        if (identifier == null || !identifier.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            throw new IllegalArgumentException("Identificador SQL no valido: " + identifier);
        }
    }
}
