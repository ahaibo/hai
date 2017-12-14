/**
 *
 */
package com.hai.ijavase.iservlet.iusermanager.impl;

import com.hai.common.db.DBUtil;
import com.hai.ijavase.iservlet.iusermanager.dao.IUserService;
import com.hai.ijavase.iservlet.iusermanager.entity.User;
import com.hai.ijavase.util.VerifyUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class UserDao implements IUserService {
    private DBUtil dbBase;

    private UserDao() {
        dbBase = DBUtil.newInstance();
    }

    @Override
    public List<User> getAllUsers(String sql) throws IOException {
        return getAllUsers(sql, null);
    }

    @Override
    public List<User> getAllUsers(String sql, int pageNow, int pageSize) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the dbBase
     */
    public DBUtil getDbBase() {
        return dbBase;
    }

    /**
     * @param dbBase the dbBase to set
     */
    public void setDbBase(DBUtil dbBase) {
        this.dbBase = dbBase;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.hai.hai.ijavase.iservlet.iusermanager.dao.IUserService#deleteUserById(int)
     */
    @Override
    public int deleteUserById(String sql, int id) throws IOException {
        return dbBase.update(sql, new Object[]{id});
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.hai.hai.ijavase.iservlet.iusermanager.dao.IUserService#getAllUsers(java.lang
     * .String, java.lang.Object[])
     */
    @Override
    public List<User> getAllUsers(String sql, Object[] params) throws IOException {
        ResultSet resultSet = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        List<User> users = null;

        // 此处为了在Dao层处理ResultSet，将底层的几个连接对象传递到此处。
        // 该map包含了 resultSet statement preparedStatement connection
        Map<String, Object> resultMap = dbBase.query(sql, params);
        if (null == resultMap || resultMap.isEmpty()) {
            return Collections.emptyList();
        }

        try {
            resultSet = (ResultSet) resultMap.get("resultSet");
            if (null == resultSet) {
                return Collections.emptyList();
            }
            statement = (Statement) resultMap.get("statement");
            preparedStatement = (PreparedStatement) resultMap.get("preparedStatement");
            connection = (Connection) resultMap.get("connection");

            users = new ArrayList<>();
            User user = null;

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String pass = resultSet.getString("pass");
                String sex = resultSet.getString("sex");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String hobby = resultSet.getString("hobby");

                user = new User(id, name, pass, sex, age, email, hobby);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbBase.closeAll(resultSet, preparedStatement, statement, connection);
        }

        return users;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.hai.hai.ijavase.iservlet.iusermanager.dao.IUserService#getUserById(int)
     */
    @Override
    public User getUserById(int id) throws IOException {
        ResultSet resultSet = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        String sql = "select * from iuser where id=?";
        Object[] params = {id};
        User user = null;
        Map<String, Object> resultMap = this.dbBase.query(sql, params);
        if (VerifyUtil.isEmpty(resultMap)) {
            return null;
        }

        resultSet = (ResultSet) resultMap.get("resultSet");
        if (null == resultSet) {
            return null;
        }
        statement = (Statement) resultMap.get("statement");
        preparedStatement = (PreparedStatement) resultMap.get("preparedStatement");
        connection = (Connection) resultMap.get("connection");

        try {
            resultSet.first();
            int userId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String pass = resultSet.getString("pass");
            String sex = resultSet.getString("sex");
            int age = resultSet.getInt("age");
            String email = resultSet.getString("email");
            String hobby = resultSet.getString("hobby");

            user = new User(userId, name, pass, sex, age, email, hobby);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.dbBase.closeAll(resultSet, preparedStatement, statement, connection);
        }

        return user;
    }

    public static UserDao newInstance() {
        return new UserDao();
    }

}
