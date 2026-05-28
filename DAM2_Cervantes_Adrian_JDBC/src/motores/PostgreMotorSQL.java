package motores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreMotorSQL implements MotorSQL {
    private static final String PORT = "5432";
    private static final String DATABASE = "cervantes-adrian-dam";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";
    private static final String URL = "jdbc:postgresql://cervantes-adrian-dam.cvzkjwjhspqm.us-east-1.rds.amazonaws.com";
    private static final String DRIVER =
            "org.postgresql.Driver";
    private Connection connection;

    @Override
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    @Override
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("No se pudo cerrar la conexion: " + e.getMessage());
            }
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
