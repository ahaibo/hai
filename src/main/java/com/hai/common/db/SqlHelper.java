package com.hai.common.db;

import com.hai.common.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 韩顺平JDBC视频教程的DBHelper类
 *
 * @author Administrator
 */
public class SqlHelper {
    // 定义变量
    private static Connection connection = null;
    // 大多数情况下用preparedstatement替代statement
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    // 连接数据库的参数
    private static String url = "";
    private static String username = "";
    private static String driver = "";
    private static String passwd = "";

    private static CallableStatement callableStatement = null;

    public static CallableStatement getCs() {
        return callableStatement;
    }

    private static Properties props = null;
    // private static FileInputStream fis = null;
    private static InputStream in = null;

    // 加载驱动，只需要一次，用静态代码块
    static {
        try {
            // 从dbinfo.properties
            props = new Properties();
            // fis = new FileInputStream("dbinfo.properties");
            in = SqlHelper.class.getClassLoader().getResourceAsStream(Constants.DEFAULT_DB_PROPERTIES_FILA_PATH);
            props.load(in);
            url = props.getProperty("url");
            username = props.getProperty("username");
            driver = props.getProperty("driver");
            passwd = props.getProperty("passwd");

            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            in = null;// 垃圾回收站上收拾
        }

    }

    // 得到连接
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, username, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    // *************callPro1存储过程函数1*************
    public static CallableStatement callPro1(String sql, String[] parameters) {
        try {
            connection = getConnection();
            callableStatement = connection.prepareCall(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    callableStatement.setObject(i + 1, parameters[i]);
                }
            }
            callableStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
            close(resultSet, callableStatement, connection);
        }
        return callableStatement;
    }

    // *******************callpro2存储过程2************************
    public static CallableStatement callPro2(String sql, String[] inparameters, Integer[] outparameters) {
        try {
            connection = getConnection();
            callableStatement = connection.prepareCall(sql);
            if (inparameters != null) {
                for (int i = 0; i < inparameters.length; i++) {
                    callableStatement.setObject(i + 1, inparameters[i]);
                }
            }
            // cs.registerOutparameter(2,oracle.jdbc.OracleTypes.CURSOR);
            if (outparameters != null) {
                for (int i = 0; i < outparameters.length; i++) {
                    callableStatement.registerOutParameter(inparameters.length + 1 + i, outparameters[i]);
                }
            }
            callableStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return callableStatement;
    }

    public static ResultSet executeQuery(String sql, String[] parameters) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    preparedStatement.setString(i + 1, parameters[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return resultSet;
    }

    public static void executeUpdate2(String[] sql, String[][] parameters) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            for (int i = 0; i < sql.length; i++) {

                if (null != parameters[i]) {
                    preparedStatement = connection.prepareStatement(sql[i]);
                    for (int j = 0; j < parameters[i].length; j++) {
                        preparedStatement.setString(j + 1, parameters[i][j]);
                    }
                    preparedStatement.executeUpdate();
                }

            }

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        } finally {
            close(resultSet, preparedStatement, connection);
        }

    }

    // 先写一个update、delete、insert
    // sql格式：update 表名 set 字段名 =？where 字段=？
    // parameter神应该是（”abc“,23）
    public static void executeUpdate(String sql, String[] parameters) {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    preparedStatement.setString(i + 1, parameters[i]);
                }

            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();// 开发阶段
            // 抛出异常
            // 可以处理，也可以不处理
            throw new RuntimeException(e.getMessage());
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    public static void close(ResultSet rs, Statement ps, Connection ct) {
        // 关闭资源(先开后关)
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ps = null;
        }
        if (null != ct) {
            try {
                ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ct = null;
        }
    }

    /**
     * @return the preparedStatement
     */
    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * @return the resultSet
     */
    public static ResultSet getResultSet() {
        return resultSet;
    }
}