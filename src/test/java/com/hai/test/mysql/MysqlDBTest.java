/**
 *
 */
package com.hai.test.mysql;

import com.alibaba.fastjson.JSONObject;
import com.hai.common.db.DBUtil;
import com.hai.common.util.RandomUtil;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;


/**
 * @author Administrator
 */
public class MysqlDBTest {

    static String propFilePath = "properties/MysqlDB.properties";

    public static void main(String[] args) throws IOException {
        String sql = "insert into user(name,sex,age,email,birthday,money) values(?,?,?,?,?,?)";
        int age = RandomUtil.random(18, 40);
        float money = RandomUtil.random(1000f, 100 * 10000f);
        Object[] params = {"lisi", "M", age, "testemail" + age + "@sohu.com", new Date(), money};

        int result = DBUtil.newInstance(propFilePath).update(sql, params);

        System.out.println("DBBase.newInstance(propFilePath).update(sql, params) result:\t" + result);
    }


    static String date = "2021-03-04 15:00:00";

    @Test
    public void queryLottery() throws Exception {
        String table = "lottery";
        // String sql = "select * from lottery";
        String sql = String.format("select * from %s %s", table, "where id in (43,47,50)");
        // String sql = String.format("select * from %s %s", table, "where lottery_id in (42,47,50)");
        // String sql = "select * from lottery_play_class_group_attribute where lottery_id = 43";
        // int age = RandomUtil.random(18, 40);
        // float money = RandomUtil.random(1000f, 100 * 10000f);
        // Object[] params = {"lisi", "M", age, "testemail" + age + "@sohu.com", new Date(), money};

        DBUtil dbUtil = DBUtil.newInstance(propFilePath);
        Map<String, Object> map = dbUtil.query(sql);
        ResultSet resultSet = (ResultSet) map.get("resultSet");
        Statement statement = (Statement) map.get("statement");
        PreparedStatement preparedStatement = (PreparedStatement) map.get("preparedStatement");
        Connection connection = (Connection) map.get("connection");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder sb = new StringBuilder();
        Object[][] columnInfos = new Object[columnCount][2];
        for (int i = 1; i <= columnCount; i++) {
            columnInfos[i - 1] = new Object[]{metaData.getColumnName(i), metaData.getColumnClassName(i)};
            sb.append(metaData.getColumnName(i)).append("\t");
        }

        sb.append("\n");
        System.out.println(JSONObject.toJSONString(columnInfos));

        int lotteryId = 125;
        Object[][] lotteryInfo = new Object[][]{{32, 125, "宾果时时彩"}, {33, 126, "宾果PK10"}, {34, 127, "宾果快三"}};
        StringBuilder insert = new StringBuilder();
        insert.append("INSERT INTO `").append(table).append("` VALUES \n");
        while (resultSet.next()) {
            insert.append("(");
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                sb.append(value).append("\t");
                String columnName = (String) columnInfos[i - 1][0];
                String className = (String) columnInfos[i - 1][1];
                boolean isVarchar = "java.lang.String".equals(className) || "java.sql.Timestamp".equals(className);
                if (isVarchar) {
                    insert.append("'");
                }
                if (columnName.equals("id")) {
                    insert.append(lotteryId);
                } else if (columnName.equals("lottery_tag")) {
                    insert.append(lotteryInfo[lotteryId - 125][0]);
                } else if (columnName.equals("name")) {
                    insert.append(lotteryInfo[lotteryId - 125][2]);
                } else if (columnName.equals("create_time") || columnName.equals("update_time")) {
                    insert.append(date);
                } else {
                    insert.append(value);
                }
                if (isVarchar) {
                    insert.append("'");
                }
                insert.append(",");
            }
            sb.append("\n");
            lotteryId++;
            insert.deleteCharAt(insert.length() - 1).append("),").append("\n");
        }
        insert.deleteCharAt(insert.length() - 2).append(";");

        dbUtil.closeAll(resultSet, preparedStatement, statement, connection);

        System.out.println(sb);
        System.out.println("\n\n" + insert);
    }

    @Test
    public void queryLotteryPlay() throws Exception {
        String table = "lottery_play";
        String sql = String.format("select * from %s %s", table, "where lottery_id in (43,47,50)");

        DBUtil dbUtil = DBUtil.newInstance(propFilePath);
        Map<String, Object> map = dbUtil.query(sql);
        ResultSet resultSet = (ResultSet) map.get("resultSet");
        Statement statement = (Statement) map.get("statement");
        PreparedStatement preparedStatement = (PreparedStatement) map.get("preparedStatement");
        Connection connection = (Connection) map.get("connection");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder sb = new StringBuilder();
        Object[][] columnInfos = new Object[columnCount][2];
        for (int i = 1; i <= columnCount; i++) {
            columnInfos[i - 1] = new Object[]{metaData.getColumnName(i), metaData.getColumnClassName(i)};
            sb.append(metaData.getColumnName(i)).append("\t");
        }

        sb.append("\n");
        System.out.println(JSONObject.toJSONString(columnInfos));

        int id = 572;
        int play_id = 419;
        StringBuilder insert = new StringBuilder();
        insert.append("INSERT INTO `").append(table).append("` VALUES \n");
        while (resultSet.next()) {
            insert.append("(");
            Long lotteryId;
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                sb.append(value).append("\t");
                String columnName = (String) columnInfos[i - 1][0];
                String className = (String) columnInfos[i - 1][1];
                boolean isVarchar = "java.lang.String".equals(className) || "java.sql.Timestamp".equals(className);
                if (isVarchar) {
                    insert.append("'");
                }
                if (columnName.equals("id")) {
                    insert.append(id);
                } else if (columnName.equals("play_id")) {
                    insert.append(play_id);
                } else if (columnName.equals("lottery_id")) {
                    lotteryId = (Long) value;
                    insert.append(lotteryId == 43 ? 125 : lotteryId == 47 ? 126 : 127);
                } else if (columnName.equals("create_time") || columnName.equals("update_time")) {
                    insert.append(date);
                } else {
                    insert.append(value);
                }
                if (isVarchar) {
                    insert.append("'");
                }
                insert.append(",");
            }
            sb.append("\n");
            id++;
            play_id++;
            insert.deleteCharAt(insert.length() - 1).append("),").append("\n");
        }
        insert.deleteCharAt(insert.length() - 2).append(";");

        dbUtil.closeAll(resultSet, preparedStatement, statement, connection);

        System.out.println(sb);
        System.out.println("\n\n" + insert);
    }

    @Test
    public void queryLotteryPlayClass() throws Exception {
        String table = "lottery_play_class";
        String sql = String.format("select * from %s %s", table, "where lottery_id in (43,47,50)");

        DBUtil dbUtil = DBUtil.newInstance(propFilePath);
        Map<String, Object> map = dbUtil.query(sql);
        ResultSet resultSet = (ResultSet) map.get("resultSet");
        Statement statement = (Statement) map.get("statement");
        PreparedStatement preparedStatement = (PreparedStatement) map.get("preparedStatement");
        Connection connection = (Connection) map.get("connection");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder sb = new StringBuilder();
        Object[][] columnInfos = new Object[columnCount][2];
        for (int i = 1; i <= columnCount; i++) {
            columnInfos[i - 1] = new Object[]{metaData.getColumnName(i), metaData.getColumnClassName(i)};
            sb.append(metaData.getColumnName(i)).append("\t");
        }

        sb.append("\n");
        System.out.println(JSONObject.toJSONString(columnInfos));

        int id = 1152;
        int play_class_id = 772;
        StringBuilder insert = new StringBuilder();
        insert.append("INSERT INTO `").append(table).append("` VALUES \n");
        while (resultSet.next()) {
            insert.append("(");
            Long lotteryId;
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                sb.append(value).append("\t");
                String columnName = (String) columnInfos[i - 1][0];
                String className = (String) columnInfos[i - 1][1];
                boolean isVarchar = "java.lang.String".equals(className) || "java.sql.Timestamp".equals(className);
                if (isVarchar) {
                    insert.append("'");
                }
                if (columnName.equals("id")) {
                    insert.append(id);
                } else if (columnName.equals("play_class_id")) {
                    insert.append(play_class_id);
                } else if (columnName.equals("lottery_id")) {
                    lotteryId = (Long) value;
                    insert.append(lotteryId == 43 ? 125 : lotteryId == 47 ? 126 : 127);
                } else if (columnName.equals("create_time") || columnName.equals("update_time")) {
                    insert.append(date);
                } else {
                    insert.append(value);
                }
                if (isVarchar) {
                    insert.append("'");
                }
                insert.append(",");
            }
            sb.append("\n");
            id++;
            play_class_id++;
            insert.deleteCharAt(insert.length() - 1).append("),").append("\n");
        }
        insert.deleteCharAt(insert.length() - 2).append(";");

        dbUtil.closeAll(resultSet, preparedStatement, statement, connection);

        System.out.println(sb);
        System.out.println("\n\n" + insert);
    }

    @Test
    public void queryLotteryPlayClassGroup() throws Exception {
        String table = "lottery_play_class_group";
        String sql = String.format("select * from %s %s", table, "where lottery_id in (43,47,50)");

        DBUtil dbUtil = DBUtil.newInstance(propFilePath);
        Map<String, Object> map = dbUtil.query(sql);
        ResultSet resultSet = (ResultSet) map.get("resultSet");
        Statement statement = (Statement) map.get("statement");
        PreparedStatement preparedStatement = (PreparedStatement) map.get("preparedStatement");
        Connection connection = (Connection) map.get("connection");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder sb = new StringBuilder();
        Object[][] columnInfos = new Object[columnCount][2];
        for (int i = 1; i <= columnCount; i++) {
            columnInfos[i - 1] = new Object[]{metaData.getColumnName(i), metaData.getColumnClassName(i)};
            sb.append(metaData.getColumnName(i)).append("\t");
        }

        sb.append("\n");
        System.out.println(JSONObject.toJSONString(columnInfos));

        int id = 1881;
        int play_class_group_id = 1760;
        StringBuilder insert = new StringBuilder();
        insert.append("INSERT INTO `").append(table).append("` VALUES \n");
        while (resultSet.next()) {
            insert.append("(");
            Long lotteryId;
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                sb.append(value).append("\t");
                String columnName = (String) columnInfos[i - 1][0];
                String className = (String) columnInfos[i - 1][1];
                boolean isVarchar = "java.lang.String".equals(className) || "java.sql.Timestamp".equals(className);
                if (isVarchar) {
                    insert.append("'");
                }
                if (columnName.equals("id")) {
                    insert.append(id);
                } else if (columnName.equals("play_class_group_id")) {
                    insert.append(play_class_group_id);
                } else if (columnName.equals("lottery_id")) {
                    lotteryId = (Long) value;
                    insert.append(lotteryId == 43 ? 125 : lotteryId == 47 ? 126 : 127);
                } else if (columnName.equals("create_time") || columnName.equals("update_time")) {
                    insert.append(date);
                } else {
                    insert.append(value);
                }
                if (isVarchar) {
                    insert.append("'");
                }
                insert.append(",");
            }
            sb.append("\n");
            id++;
            play_class_group_id++;
            insert.deleteCharAt(insert.length() - 1).append("),").append("\n");
        }
        insert.deleteCharAt(insert.length() - 2).append(";");

        dbUtil.closeAll(resultSet, preparedStatement, statement, connection);

        System.out.println(sb);
        System.out.println("\n\n" + insert);
    }

    @Test
    public void queryLotteryPlayClassGroupAttrbute() throws Exception {
        String table = "lottery_play_class_group_attribute";
        String sql = String.format("select * from %s %s", table, "where lottery_id in (43,47,50)");

        DBUtil dbUtil = DBUtil.newInstance(propFilePath);
        Map<String, Object> map = dbUtil.query(sql);
        ResultSet resultSet = (ResultSet) map.get("resultSet");
        Statement statement = (Statement) map.get("statement");
        PreparedStatement preparedStatement = (PreparedStatement) map.get("preparedStatement");
        Connection connection = (Connection) map.get("connection");

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        StringBuilder sb = new StringBuilder();
        Object[][] columnInfos = new Object[columnCount][2];
        for (int i = 1; i <= columnCount; i++) {
            columnInfos[i - 1] = new Object[]{metaData.getColumnName(i), metaData.getColumnClassName(i)};
            sb.append(metaData.getColumnName(i)).append("\t");
        }

        sb.append("\n");
        System.out.println(JSONObject.toJSONString(columnInfos));

        int id = 33140;
        int play_class_group_attribute_id = 17566;
        StringBuilder insert = new StringBuilder();
        insert.append("INSERT INTO `").append(table).append("` VALUES \n");
        while (resultSet.next()) {
            insert.append("(");
            Long lotteryId;
            for (int i = 1; i <= columnCount; i++) {
                Object value = resultSet.getObject(i);
                sb.append(value).append("\t");
                String columnName = (String) columnInfos[i - 1][0];
                String className = (String) columnInfos[i - 1][1];
                boolean isVarchar = "java.lang.String".equals(className) || "java.sql.Timestamp".equals(className);
                if (isVarchar) {
                    insert.append("'");
                }
                if (columnName.equals("id")) {
                    insert.append(id);
                } else if (columnName.equals("play_class_group_attribute_id")) {
                    insert.append(play_class_group_attribute_id);
                } else if (columnName.equals("lottery_id")) {
                    lotteryId = (Long) value;
                    insert.append(lotteryId == 43 ? 125 : lotteryId == 47 ? 126 : 127);
                } else if (columnName.equals("create_time") || columnName.equals("update_time")) {
                    insert.append(date);
                } else {
                    insert.append(value);
                }
                if (isVarchar) {
                    insert.append("'");
                }
                insert.append(",");
            }
            sb.append("\n");
            id++;
            play_class_group_attribute_id++;
            insert.deleteCharAt(insert.length() - 1).append("),").append("\n");
        }
        insert.deleteCharAt(insert.length() - 2).append(";");

        dbUtil.closeAll(resultSet, preparedStatement, statement, connection);

        System.out.println(sb);
        System.out.println("\n\n" + insert);
    }
}
