package com.hai.test.mysql;

import com.hai.common.db.DBUtil;

import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * app_member account 彩票库昵称同步处理
 *
 * @author Administrator
 */
public class CpUserSync {
    static String TABLE_NAME = "app_member";

    static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String propFilePath = "properties/caipiao.properties";
//        String sql = "select id,nickname from app_member_bak where nickname is not null and nickname != '' limit 1000";
        String sql = "select id,nickname from app_member where nickname is not null and nickname != ''";

        DBUtil dbUtil = DBUtil.newInstance(propFilePath);

        Map<String, Object> map = dbUtil.query(sql);
        ResultSet resultSet = (ResultSet) map.get("resultSet");
        Statement statement = (Statement) map.get("statement");
        PreparedStatement preparedStatement = (PreparedStatement) map.get("preparedStatement");
        Connection connection = (Connection) map.get("connection");

        if (null == resultSet) {
            System.out.println("resultSet is empty.");
            return;
        }

        String propFilePath2 = "properties/xinshui.properties";
        DBUtil dbUtil2 = DBUtil.newInstance(propFilePath2);
        Connection xsconn = dbUtil2.getConnection(propFilePath2, true);
        try {
            while (resultSet.next()) {
//                executorService.execute(() -> {
//                    try {
                Integer id = resultSet.getInt("id");
                String nickname = resultSet.getString("nickname");

//                        String updateSql = "update lhc_user set nickname='" + nickname + "' where id=" + id;
                String updateSql = "update lhc_user set nickname=? where id=?";
                PreparedStatement preparedStatement1 = xsconn.prepareStatement(updateSql);
                preparedStatement1.setObject(1, nickname);
                preparedStatement1.setObject(2, updateSql);
                int effict = preparedStatement1.executeUpdate();
                System.out.println(updateSql + " efficts:" + effict);
                dbUtil.closeAll(null, preparedStatement1, null, null);
//                    } catch (SQLException e) {
//                        e.printStackTrace();
//                    }
//                });
            }
            System.out.println("finished...");
            long end = System.currentTimeMillis();
            System.out.println((end - start) + " times.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbUtil.closeAll(resultSet, preparedStatement, statement, connection);
        }
    }

}
