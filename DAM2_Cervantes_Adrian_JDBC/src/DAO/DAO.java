package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    int add(T entity) throws SQLException;

    int update(T entity) throws SQLException;

    T find(int id) throws SQLException;

    List<T> findAll() throws SQLException;
}

