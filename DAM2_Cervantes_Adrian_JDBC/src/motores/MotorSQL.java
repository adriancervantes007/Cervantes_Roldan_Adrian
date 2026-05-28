package motores;

import java.sql.Connection;
import java.sql.SQLException;

public interface MotorSQL {
    void connect() throws SQLException;

    void disconnect();

    Connection getConnection();
}
