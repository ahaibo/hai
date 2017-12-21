/**
 *
 */
package com.hai.common.db.mydatasource;

import com.hai.common.db.DBDriverProp;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author Administrator
 */
public class DBCPDataSource {
    public static void main(String[] args) throws Exception {
        Properties properties = DBDriverProp.newInstance("properties/MysqlDB.properties");
        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
    }
}
