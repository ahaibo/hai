/**
 *
 */
package com.hai.common.db.mydatasource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Administrator
 */
public class JDBCDataSource {
    private DataSource dataSource;
    private static final String DBCP_CONFIG_PROPERTIES = "dbcpConfig.properties";

    public JDBCDataSource() {
        this(null);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = this.dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return connection;
    }

    /**
     * @param propertiesPath
     */
    public JDBCDataSource(String propertiesPath) {

        propertiesPath = null == propertiesPath || propertiesPath.trim().isEmpty() ? DBCP_CONFIG_PROPERTIES
                : propertiesPath;
        Properties dbcpConfigProperties = new Properties();
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(propertiesPath);
            dbcpConfigProperties.load(is);

            this.dataSource = BasicDataSourceFactory.createDataSource(dbcpConfigProperties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @return the dataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    public static JDBCDataSource getInstance() {
        return getInstance(null);
    }

    public static JDBCDataSource getInstance(String propertiesPath) {
        return new JDBCDataSource(propertiesPath);
    }

}
