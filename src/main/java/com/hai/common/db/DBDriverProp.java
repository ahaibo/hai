/**
 *
 */
package com.hai.common.db;

import com.hai.common.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Administrator
 */
public class DBDriverProp extends Properties {

    private static final long serialVersionUID = 1077808658777121911L;
    private static String DEFAULT_DB_PROPERTIES_FILA_PATH = Constants.DEFAULT_DB_PROPERTIES_FILA_PATH;
    private String dbPropFilePath;
    private static DBDriverProp instance;

    private DBDriverProp() throws Exception {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_DB_PROPERTIES_FILA_PATH);
        try {
            load(is);
        } catch (IOException e) {
            throw new IOException("读取配置文件：".concat(DEFAULT_DB_PROPERTIES_FILA_PATH).concat(" 异常！"), e);
        }
    }

    private DBDriverProp(String dbPropFilePath) throws Exception {
        DEFAULT_DB_PROPERTIES_FILA_PATH = dbPropFilePath;
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(DEFAULT_DB_PROPERTIES_FILA_PATH);
        try {
            load(is);
        } catch (IOException e) {
            throw new IOException("读取指定的配置文件：".concat(DEFAULT_DB_PROPERTIES_FILA_PATH).concat(" 异常！"), e);
        }
    }

    public static DBDriverProp newInstance() throws Exception {
        if (null != instance) {
            return instance;
        } else {
            return newInstance(null);
        }
    }

    public static synchronized DBDriverProp newInstance(String dbPropFilePath) throws Exception {
        if (null != instance) {
            return instance;
        } else {
            return isEmpty(dbPropFilePath) ? new DBDriverProp() : new DBDriverProp(dbPropFilePath);
        }
    }

    /**
     * @return the dbPropFilePath
     */
    public String getDbPropFilePath() {
        return dbPropFilePath;
    }

    /**
     * @param dbPropFilePath the dbPropFilePath to set
     */
    public void setDbPropFilePath(String dbPropFilePath) {
        this.dbPropFilePath = dbPropFilePath;
        DEFAULT_DB_PROPERTIES_FILA_PATH = this.dbPropFilePath;
    }

    private static boolean isEmpty(String str) {
        return null == str || str.trim().isEmpty();
    }
}
