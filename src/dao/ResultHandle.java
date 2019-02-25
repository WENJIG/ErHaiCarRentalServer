package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用来转换结果集的类型
 * @param <T>
 */
@FunctionalInterface
public interface ResultHandle<T> {

    T handle(ResultSet resultSet) throws SQLException;

}
