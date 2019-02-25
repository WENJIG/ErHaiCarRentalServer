package dao;

import java.sql.SQLException;

public interface BaseDao<T> {

    java.sql.Connection getNewConnection() throws SQLException;

    boolean baseSave(String sql, Object... params);

    boolean baseUpdate(String sql, Object... params);

    T baseFind(String sql, ResultHandle<T> rh, Object... params);
}
