package DAO;
import beans.Agencia;
import beans.DetalleSatelite;
import beans.Satelite;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SateliteDAOImpl extends AbstractDAO<Satelite> {
    private static final String INSERT =
            "INSERT INTO satelites (nombre, orbita, peso, coste, activo, fecha_lanzamiento, agencia_id, autor_examen) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE satelites " +
                    "SET nombre = ?, orbita = ?, peso = ?, coste = ?, activo = ?, fecha_lanzamiento = ?, agencia_id = ?, autor_examen = ? " +
                    "WHERE id = ?";
    private static final String BASE_SELECT =
            "SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo, " +
                    "s.fecha_lanzamiento, s.autor_examen AS s_autor, " +
                    "a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion, " +
                    "a.autor_examen AS a_autor " +
                    "FROM satelites s " +
                    "INNER JOIN agencias a ON s.agencia_id = a.id";
    private static final String FIND = BASE_SELECT + " WHERE s.id = ?";
    private static final String FIND_ALL = BASE_SELECT + " ORDER BY s.id";
    private static final String FIND_BY_AGENCIA = BASE_SELECT + " WHERE a.id = ? ORDER BY s.id";
    private static final String FIND_WITH_DETAIL =
            "SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo, " +
                    "s.fecha_lanzamiento, s.autor_examen AS s_autor, " +
                    "a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion, " +
                    "a.autor_examen AS a_autor, " +
                    "d.id AS d_id, d.velocidad_maxima, d.combustible, d.vida_util, " +
                    "d.temperatura_maxima, d.autor_examen AS d_autor " +
                    "FROM satelites s " +
                    "INNER JOIN agencias a ON s.agencia_id = a.id " +
                    "INNER JOIN detalle_satelite d ON d.satelite_id = s.id " +
                    "WHERE s.id = ?";
    private static final String FIND_ACTIVE_WITH_DETAIL =
            "SELECT s.id AS s_id, s.nombre AS s_nombre, s.orbita, s.peso, s.coste, s.activo, " +
                    "s.fecha_lanzamiento, s.autor_examen AS s_autor, " +
                    "a.id AS a_id, a.nombre AS a_nombre, a.pais, a.fecha_fundacion, " +
                    "a.autor_examen AS a_autor, " +
                    "d.id AS d_id, d.velocidad_maxima, d.combustible, d.vida_util, " +
                    "d.temperatura_maxima, d.autor_examen AS d_autor " +
                    "FROM satelites s " +
                    "INNER JOIN agencias a ON s.agencia_id = a.id " +
                    "INNER JOIN detalle_satelite d ON d.satelite_id = s.id " +
                    "WHERE s.activo = TRUE " +
                    "ORDER BY s.id";

    @Override
    public int add(Satelite satelite) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, satelite.getNombre());
            ps.setString(2, satelite.getOrbita());
            ps.setBigDecimal(3, satelite.getPeso());
            ps.setBigDecimal(4, satelite.getCoste());
            ps.setBoolean(5, satelite.isActivo());
            ps.setDate(6, Date.valueOf(satelite.getFechaLanzamiento()));
            ps.setInt(7, satelite.getAgencia().getId());
            ps.setString(8, AUTOR_EXAMEN);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    satelite.setId(rs.getInt(1));
                }
            }
            return satelite.getId();
        } finally {
            closeConnection();
        }
    }

    @Override
    public int update(Satelite satelite) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            ps.setString(1, satelite.getNombre());
            ps.setString(2, satelite.getOrbita());
            ps.setBigDecimal(3, satelite.getPeso());
            ps.setBigDecimal(4, satelite.getCoste());
            ps.setBoolean(5, satelite.isActivo());
            ps.setDate(6, Date.valueOf(satelite.getFechaLanzamiento()));
            ps.setInt(7, satelite.getAgencia().getId());
            ps.setString(8, AUTOR_EXAMEN);
            ps.setInt(9, satelite.getId());
            return ps.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Satelite find(int id) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(FIND)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapSatelite(rs, false) : null;
            }
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Satelite> findAll() throws SQLException {
        List<Satelite> satelites = new ArrayList<>();
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                satelites.add(mapSatelite(rs, false));
            }
            return satelites;
        } finally {
            closeConnection();
        }
    }

    public List<Satelite> findByAgencia(int agenciaId) throws SQLException {
        List<Satelite> satelites = new ArrayList<>();
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_AGENCIA)) {
            ps.setInt(1, agenciaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    satelites.add(mapSatelite(rs, false));
                }
            }
            return satelites;
        } finally {
            closeConnection();
        }
    }

    public Satelite findWithDetail(int id) throws SQLException {
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_WITH_DETAIL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapSatelite(rs, true) : null;
            }
        } finally {
            closeConnection();
        }
    }

    public List<Satelite> findActiveWithAgencyAndDetail() throws SQLException {
        List<Satelite> satelites = new ArrayList<>();
        try (Connection connection = openConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_ACTIVE_WITH_DETAIL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                satelites.add(mapSatelite(rs, true));
            }
            return satelites;
        } finally {
            closeConnection();
        }
    }

    public int updateDynamic(int id, Map<String, Object> fields) throws SQLException {
        return executeDynamicUpdate("satelites", "id", id, fields);
    }

    private Satelite mapSatelite(ResultSet rs, boolean includeDetail) throws SQLException {
        Agencia agencia = new Agencia(
                rs.getInt("a_id"),
                rs.getString("a_nombre"),
                rs.getString("pais"),
                rs.getDate("fecha_fundacion").toLocalDate(),
                rs.getString("a_autor")
        );

        DetalleSatelite detalle = null;
        if (includeDetail) {
            detalle = new DetalleSatelite(
                    rs.getInt("d_id"),
                    rs.getBigDecimal("velocidad_maxima"),
                    rs.getString("combustible"),
                    rs.getInt("vida_util"),
                    rs.getBigDecimal("temperatura_maxima"),
                    rs.getString("d_autor")
            );
        }

        return new Satelite(
                rs.getInt("s_id"),
                rs.getString("s_nombre"),
                rs.getString("orbita"),
                rs.getBigDecimal("peso"),
                rs.getBigDecimal("coste"),
                rs.getBoolean("activo"),
                rs.getDate("fecha_lanzamiento").toLocalDate(),
                agencia,
                detalle,
                rs.getString("s_autor")
        );
    }
}
