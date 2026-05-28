package DAO;

import beans.Agencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AgenciaDAOImpl extends AbstractDAO<Agencia> {
    private static final String INSERT =
            "INSERT INTO agencias (nombre, pais, fecha_fundacion, autor_examen) " +
                    "VALUES (?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE agencias " +
                    "SET nombre = ?, pais = ?, fecha_fundacion = ?, autor_examen = ? " +
                    "WHERE id = ?";
    private static final String FIND =
            "SELECT id, nombre, pais, fecha_fundacion, autor_examen " +
                    "FROM agencias " +
                    "WHERE id = ?";
    private static final String FIND_ALL =
            "SELECT id, nombre, pais, fecha_fundacion, autor_examen " +
                    "FROM agencias " +
                    "ORDER BY id";

    @Override
    public int add(Agencia agencia) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, agencia.getNombre());
            ps.setString(2, agencia.getPais());
            ps.setDate(3, Date.valueOf(agencia.getFechaFundacion()));
            ps.setString(4, AUTOR_EXAMEN);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    agencia.setId(rs.getInt(1));
                }
            }
            return agencia.getId();
        } finally {
            closeConnection();
        }
    }

    @Override
    public int update(Agencia agencia) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, agencia.getNombre());
            ps.setString(2, agencia.getPais());
            ps.setDate(3, Date.valueOf(agencia.getFechaFundacion()));
            ps.setString(4, AUTOR_EXAMEN);
            ps.setInt(5, agencia.getId());
            return ps.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Agencia find(int id) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(FIND)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapAgencia(rs) : null;
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Agencia> findAll() throws SQLException {
        List<Agencia> agencias = new ArrayList<>();
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                agencias.add(mapAgencia(rs));
            }
            return agencias;
        } finally {
            closeConnection();
        }
    }

    private Agencia mapAgencia(ResultSet rs) throws SQLException {
        return new Agencia(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("pais"),
                rs.getDate("fecha_fundacion").toLocalDate(),
                rs.getString("autor_examen")
        );
    }
}
