/**
 *
 */
package com.hai.common.db.mydatasource;

import com.hai.common.db.DBDriverProp;
import com.hai.common.util.VerifyUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;

/**
 * 自定义连接池【数据源】
 *
 * @author Administrator
 */
public class MyDataSource {
    private static final int INIT_CONN_COUNT = 2;
    private static final int MAX_CONN_COUNT = 2;
    private int currConnCount = 0;

    private LinkedList<Connection> connectionPool = new LinkedList<>();

    public MyDataSource() throws Exception {
        for (int i = 0; i < INIT_CONN_COUNT; i++) {
            connectionPool.add(createConnection());
            this.currConnCount++;
        }
    }

    public static MyDataSource getInstance() throws Exception {
        return new MyDataSource();
    }

    /**
     * @param connection
     */
    public void addConnection(Connection connection) {
        this.connectionPool.addLast(connection);
    }

    public synchronized Connection getConnection() throws Exception {
        if (this.connectionPool.size() > 0) {
            return this.connectionPool.removeFirst();
        } else {
            if (this.currConnCount < MAX_CONN_COUNT) {
                this.currConnCount++;
                return createConnection();
            } else {
                throw new Exception("当前连接已用完，请稍候。。");
            }
        }

    }

    /**
     * @return
     * @throws Exception
     */
    private Connection createConnection() throws Exception {
        String properties = "properties/MysqlDB.properties";
        return createConnection(properties);
    }

    /**
     * @param properties
     * @return
     * @throws Exception
     */
    private Connection createConnection(String properties) throws Exception {
        Connection connection = null;
        MyConnection proxyConnection = null;
        DBDriverProp dbDriverProp = DBDriverProp.newInstance(properties);

        if (null != dbDriverProp) {
            String className = dbDriverProp.containsKey("DRIVER_CLASS") ? dbDriverProp.getProperty("DRIVER_CLASS")
                    : null;
            String url = dbDriverProp.containsKey("URL") ? dbDriverProp.getProperty("URL") : null;
            String userName = dbDriverProp.containsKey("USER_NAME") ? dbDriverProp.getProperty("USER_NAME") : null;
            String userPass = dbDriverProp.containsKey("USER_PASS") ? dbDriverProp.getProperty("USER_PASS") : null;

            if (VerifyUtil.isEmpty(className) || VerifyUtil.isEmpty(url) || VerifyUtil.isEmpty(userName)
                    || VerifyUtil.isEmpty(userPass)) {
                return connection;
            }

            Class.forName(className);

            connection = DriverManager.getConnection(url, userName, userPass);

            // 返回代理的Connection【改变原Connection的close方法处理逻辑】
            proxyConnection = new MyConnection(this);
        }

        return proxyConnection.bind(connection);
    }

    public void free(Connection connection) {
        if (connection instanceof MyConnection) {
            this.connectionPool.addLast(connection);
        }
    }

    /**
     * @return the currConnCount
     */
    public int getCurrConnCount() {
        return currConnCount;
    }

    /**
     * @param currConnCount the currConnCount to set
     */
    public void setCurrConnCount(int currConnCount) {
        this.currConnCount = currConnCount;
    }
}
