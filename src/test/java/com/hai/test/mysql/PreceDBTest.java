/**
 *
 */
package com.hai.test.mysql;

import com.hai.common.db.DBUtil;
import com.hai.common.util.RandomUtil;
import jnr.ffi.Struct;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;


/**
 * app_member account 去重处理
 * @author Administrator
 */
public class PreceDBTest {
    static String TABLE_NAME = "app_member";

    public static void main(String[] args) throws IOException {
        String propFilePath = "properties/precp.properties";
        String sql = "select account from " + TABLE_NAME + " WHERE deleted = 0 GROUP BY LOWER(account) HAVING count(LOWER(account)) > 1 ORDER BY account;";

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

        try {
//            while (resultSet.next()) {
//                String account = resultSet.getString(1);
//                String queryRepeatAccountSql = "SELECT id,account,phone,vip_id,bet_amount,pay_amount,real_name,user_type from " + TABLE_NAME + " where LOWER(account) like '" + account.toLowerCase() + "' order by pay_amount asc";
//                PreparedStatement preparedStatement2 = connection.prepareStatement(queryRepeatAccountSql);
//                ResultSet resultSet2 = preparedStatement2.executeQuery();
//                if (null == resultSet2) {
//                    continue;
//                }
//
//                List<Map<String, Object>> entries = new ArrayList<>();
//                while (resultSet2.next()) {
//                    Object id = resultSet2.getObject("id");
//                    Object currAccount = resultSet2.getObject("account");
//                    Object phone = resultSet2.getObject("phone");
//                    Object vip_id = resultSet2.getObject("vip_id");
//                    Object bet_amount = resultSet2.getObject("bet_amount");
//                    Object pay_amount = resultSet2.getObject("pay_amount");
//                    Object real_name = resultSet2.getObject("real_name");
//                    Object user_type = resultSet2.getObject("user_type");
//
//                    String delimiter = ", ";
//                    StringBuilder row = new StringBuilder();
//                    row.append("id=").append(id).append(delimiter);
//                    row.append("account=").append(currAccount).append(delimiter);
//                    row.append("phone=").append(phone).append(delimiter);
//                    row.append("bet_amount=").append(bet_amount).append(delimiter);
//                    row.append("pay_amount=").append(pay_amount).append(delimiter);
//                    row.append("real_name=").append(real_name).append(delimiter);
//                    row.append("vip_id=").append(vip_id).append(delimiter);
//                    row.append("user_type=").append(user_type);
//                    //System.out.println(row);
//
//                    Map<String, Object> item = new HashMap<>();
//                    item.put("id", id);
//                    item.put("account", currAccount);
//                    item.put("phone", phone);
//                    item.put("bet_amount", bet_amount);
//                    item.put("pay_amount", pay_amount);
//                    item.put("real_name", real_name);
//                    item.put("vip_id", vip_id);
//                    item.put("user_type", user_type);
//                    entries.add(item);
//                }
//
//                int size = entries.size();
//                if (size == 0) {
//                    System.out.println("size=0, sql: " + queryRepeatAccountSql);
//                } else {
//                    //System.out.println("size: " + entries.size() + "\n");
//                    updateAccount(entries, connection, dbUtil);
//                }
//
//                dbUtil.closeAll(resultSet2, preparedStatement2, null, null);
//            }
            System.out.println("finished...");
        }/* catch (SQLException e) {
            e.printStackTrace();
        } */finally {
            dbUtil.closeAll(resultSet, preparedStatement, statement, connection);
        }

    }

    private static void updateAccount(List<Map<String, Object>> entries, Connection connection, DBUtil dbUtil) throws SQLException {
        int size = entries.size();
        String suffix = "RENAME";
        String accountLabel = "account";
        String phoneLabel = "phone";
        if (size == 2) {
            //两条记录中去phone为空的重命名
            Map<String, Object> entry1 = entries.get(0);
            Map<String, Object> entry2 = entries.get(1);

            String phone1 = (String) entry1.get(phoneLabel);
            String phone2 = (String) entry2.get(phoneLabel);
            String account = (String) entry1.get(accountLabel);
            if (null != phone1 && null == phone2) {
                account = (String) entry2.get(accountLabel);
            }
            toUpdateAccount(account + suffix, account, connection, dbUtil);
        } else {
            System.out.println();
            for (int i = 0, len = size - 1; i < len; i++) {
                Map<String, Object> entry1 = entries.get(i);
                String account = (String) entry1.get(accountLabel);
                toUpdateAccount(account + suffix + (i + 1), account, connection, dbUtil);
            }
            System.out.println();
        }
    }

    private static int toUpdateAccount(String account, String oldAccount, Connection connection, DBUtil dbUtil) throws SQLException {
        String sql = "update " + TABLE_NAME + " set account='" + account + "' where account='" + oldAccount + "';";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int rows = preparedStatement.executeUpdate();
        System.out.println(oldAccount + "\t\t\t" + account);
        dbUtil.closeAll(null, preparedStatement, null, null);
        return rows;
    }
}
