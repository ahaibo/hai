package com.hai.db.jfinal;

import java.io.IOException;
import java.sql.SQLException;

public class JFinalTest {
    public static void main(String[] args) {
        PropKit.use("config.txt");
        MySqlInfo mySqlInfo = new MySqlInfo(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
        MysqlExport mysqlExport = new MysqlExport(mySqlInfo);
        try {
            mysqlExport.export();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
