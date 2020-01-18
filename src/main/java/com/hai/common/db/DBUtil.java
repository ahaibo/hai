/**
 *
 */
package com.hai.common.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class DBUtil {
    private String propFilePath;

    public DBUtil() {
    }

    /**
     * @param propFilePath
     */
    public DBUtil(String propFilePath) {
        this.propFilePath = propFilePath;
    }

    public Connection getConnection() throws IOException {
        return getConnection(false);
    }

    public Connection getConnection(boolean forceInstance) throws IOException {
        return getConnection(null, forceInstance);
    }

    /**
     * 获取连接
     *
     * @param propFilePath
     * @return
     * @throws IOException
     */
    public Connection getConnection(String propFilePath, boolean forceInstance) throws IOException {
        Connection connection = null;

        // 读取驱动配置信息
        DBDriverProp dbDriverProp = null;
        try {
            if (forceInstance) {
                dbDriverProp = new DBDriverProp(propFilePath);
            } else {
                dbDriverProp = DBDriverProp.newInstance(propFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println(e.getMessage());
        }

        String className = dbDriverProp.containsKey("DRIVER_CLASS") ? dbDriverProp.getProperty("DRIVER_CLASS") : null;
        String url = dbDriverProp.containsKey("URL") ? dbDriverProp.getProperty("URL") : null;
        String userName = dbDriverProp.containsKey("USER_NAME") ? dbDriverProp.getProperty("USER_NAME") : null;
        String userPass = dbDriverProp.containsKey("USER_PASS") ? dbDriverProp.getProperty("USER_PASS") : null;

        if (isEmpty(className) || isEmpty(url) || isEmpty(userName) || isEmpty(userPass)) {
            return connection;
        }

        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url, userName, userPass);
            System.out.println("\n\n连接数据库成功：" + url + "\n\n");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Map<String, Object> query(String sql) throws IOException {
        return query(sql, null);
    }

    /**
     * 执行查询操作，返回结果集
     *
     * @param sql
     * @param params
     * @return
     * @throws IOException
     */
    public Map<String, Object> query(String sql, Object[] params) throws IOException {
        Map<String, Object> map = new HashMap<>();
        ResultSet resultSet = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        connection = isEmpty(propFilePath) ? getConnection() : getConnection(propFilePath, false);

        if (isEmpty(params)) {
            try {
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultSet = statement.executeQuery(sql);

                makeResult(map, resultSet, statement, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                /*
                 * 解决异常：java.sql.SQLException: 对只转发结果集的无效操作: first
                 *
                 * 1. ResultSet.TYPE_FORWARD_ONLY (默认方式，略) <br/>
                 * 2.ResultSet.TYPE_SCROLL_INSENSITIVE
                 * 双向滚动，但不及时更新，就是如果数据库里的数据修改过，并不在ResultSet中反应出来。 <br/>
                 * 3.ResultSet.TYPE_SCROLL_SENSITIVE
                 * 双向滚动，并及时跟踪数据库里的更新,以便更改ResultSet中的数据。<br/>
                 * 4.ResultSet.CONCUR_READ_ONLY 只读取ResultSet <br/>
                 * 5.ResultSet.CONCUR_UPDATABLE 用ResultSet更新数据库
                 */
                preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                setSqlParameter(preparedStatement, params);
                resultSet = preparedStatement.executeQuery();

                makeResult(map, resultSet, statement, preparedStatement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //在外层关闭
        //closeAll(resultSet, preparedStatement, statement, connection);

        return map;
    }

    /**
     * 封装结果，将几个连接封装到map传递到上层
     *
     * @param resultSet
     * @param statement
     * @param preparedStatement
     * @param connection
     */
    private void makeResult(Map<String, Object> map,
                            ResultSet resultSet,
                            Statement statement,
                            PreparedStatement preparedStatement, Connection connection) {
        map.put("resultSet", resultSet);
        map.put("statement", statement);
        map.put("preparedStatement", preparedStatement);
        map.put("connection", connection);
    }

    /**
     * 执行增、删、改等操作
     *
     * @param sql
     * @param params
     * @return
     * @throws IOException
     */
    public int update(String sql, Object[] params) throws IOException {
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        int result = 0;

        connection = isEmpty(propFilePath) ? getConnection() : getConnection(propFilePath, false);

        if (isEmpty(params)) {
            try {
                statement = connection.createStatement();
                result = statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll(null, preparedStatement, statement, connection);
            }
        } else {
            try {
                preparedStatement = connection.prepareStatement(sql);
                setSqlParameter(preparedStatement, params);
                result = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeAll(null, preparedStatement, statement, connection);
            }
        }
        return result;
    }

    /**
     * 集中关闭连接
     *
     * @param resultSet
     * @param preparedStatement
     * @param statement
     * @param connection
     */
    public void closeAll(ResultSet resultSet, PreparedStatement preparedStatement, Statement statement,
                         Connection connection) {
        try {
            if (!isEmpty(resultSet) && !resultSet.isClosed()) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (!isEmpty(preparedStatement) && !preparedStatement.isClosed()) {
                preparedStatement.close();
                preparedStatement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (!isEmpty(statement) && !statement.isClosed()) {
                statement.close();
                statement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (!isEmpty(connection) && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param preparedStatement
     * @param params
     */
    private void setSqlParameter(PreparedStatement preparedStatement, Object[] params) {
        for (int i = 0; i < params.length; i++) {
            try {
                preparedStatement.setObject((i + 1), params[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isEmpty(String str) {
        return null == str || str.trim().isEmpty();
    }

    private boolean isEmpty(Object obj) {
        return null == obj;
    }

    private boolean isEmpty(Object[] arr) {
        return null == arr || arr.length == 0;
    }

    public static DBUtil newInstance() {
        return new DBUtil();
    }

    public static DBUtil newInstance(String propFilePath) {
        return new DBUtil(propFilePath);
    }

    /**
     * @return the propFilePath
     */
    public String getPropFilePath() {
        return propFilePath;
    }

    /**
     * @param propFilePath the propFilePath to set
     */
    public void setPropFilePath(String propFilePath) {
        this.propFilePath = propFilePath;
    }
}
