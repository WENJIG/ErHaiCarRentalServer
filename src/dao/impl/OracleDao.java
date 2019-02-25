package dao.impl;

import dao.BaseDao;
import dao.ResultHandle;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * oracle数据库的基础dao
 */
public class OracleDao<T> implements BaseDao {

    private static String driverName;
    private String url;
    private String username;
    private String password;

    /**
     * @Description: 优先从配置文件加载初始化
     * @param []
     * @Return
     */
    OracleDao() {
        InputStream is = OracleDao.class.getResourceAsStream("odbc.properties");
        Properties pro = new Properties();
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverName = pro.getProperty("DriverName");
        url = pro.getProperty("URL");
        username = pro.getProperty("username");
        password = pro.getProperty("password");
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * @Description: 使用PreparedStatement 处理sql 语句中的占位符
     * @param [connection, sql, params]
     * @Return java.sql.PreparedStatement
     */
    protected PreparedStatement baseInitParams(Connection connection,String sql, Object... params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 用反射将一个对象生成sql并且执行添加方法
     * @param [sqlTemplate, clazz]
     * @Return boolean
     */
    protected boolean reflectInit(String sqlTemplate, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        Object[] objects = new Object[fields.length - 1];
        StringBuffer stringBuffer = new StringBuffer(sqlTemplate);
        for (int i = 0; i < objects.length; i++) {
            stringBuffer.append(",?");
            fields[i + 1].setAccessible(true);
            try {
                objects[i] = fields[i + 1].get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        stringBuffer.append(")");
        String sql = stringBuffer.toString();
        try {
            Method method = this.getClass().getMethod("baseSave", String.class, Object[].class);
            try {
                return (boolean) method.invoke(this, sql, objects);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                return false;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Connection getNewConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    /**
     * @Description: 处理一些基础的存储
     * @param [sql, params]
     * @Return boolean
     */
    @Override
    public boolean baseSave(String sql, Object... params) {
        Connection connection;
        try {
            connection = getNewConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        PreparedStatement preparedStatement = baseInitParams(connection, sql, params);
        if (preparedStatement != null) {
            try {
                if (preparedStatement.executeUpdate() > 0) {
                    connection.commit();
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                //e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @Description: 处理一些基础的更新(删除也算更新吧，应该?)
     * @param [sql, params]
     * @Return boolean
     */
    @Override
    public boolean baseUpdate(String sql, Object... params) {
        Connection connection;
        try {
            connection = getNewConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        PreparedStatement preparedStatement = baseInitParams(connection, sql, params);
        try {
            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * @Description: 处理一些基础的查询
     * @param [sql, params]
     * @Return java.sql.ResultSet
     */
    @Override
    public T baseFind(String sql, ResultHandle rh, Object... params) {
        Connection connection;
        try {
            connection = getNewConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        PreparedStatement preparedStatement = baseInitParams(connection, sql, params);
        ArrayList<?> arrayList = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            return (T) rh.handle(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
