/**
 *
 */
package com.hai.common.db.mydatasource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hai.common.util.VerifyUtil;

/**
 * @param <T>
 * @author Administrator
 */
public class MyDBUtil {
    private MyDataSource myDataSource;

    private MyDBUtil() {
        try {
            this.myDataSource = MyDataSource.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MyDBUtil getInstance() {
        return new MyDBUtil();
    }

    public int execute(String sql, Object[] params) throws Exception {
        int result = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        conn = myDataSource.getConnection();

        ps = conn.prepareStatement(sql);
        setPramaters(ps, params);
        result = ps.executeUpdate();
        this.close(null, ps, null, conn);

        return result;
    }

    /**
     * @param params
     * @throws SQLException
     */
    private void setPramaters(PreparedStatement ps, Object[] params) throws SQLException {
        if (VerifyUtil.isEmpty(params)) {
            return;
        }

        for (int i = 0; i < params.length; i++) {
            ps.setObject((i + 1), params[i]);
        }
    }

    /**
     * 利用JDBC操作数据库元数据级JAVA反射把结果集直接转为相应的实体集合
     *
     * @param clazz
     * @param sql
     * @param params
     * @return
     */
    public <T> List<T> queryList(Class<T> clazz, String sql, Object[] params) {
        List<T> resultList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = this.myDataSource.getConnection();
            ps = conn.prepareStatement(sql);
            setPramaters(ps, params);

            rs = ps.executeQuery();

            if (VerifyUtil.isNotEmpty(rs) && !rs.next()) {
                return resultList;
            }
            // 因为上边调用了rs.next()，所以此处要调用此方法将游标移回去，否则会出现数据少读一行的结果
            rs.previous();

            // 获取数据库相关的元数据，用以和JAVA反射公用实现结果集到对象实体对象的封装转换
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int colCount = resultSetMetaData.getColumnCount();

            // 记录数据库查询出的字段名，并处理为实体类里的set方法前面形式。如有name的元数据，则转换为setName形式
            String[] colNames = new String[colCount];

            Method[] methods = clazz.getMethods();

            for (int i = 0; i < colCount; i++) {
                colNames[i] = resultSetMetaData.getColumnLabel((i + 1));
            }

            while (rs.next()) {
                constructResultObjects(clazz, resultList, rs, colNames, methods);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close(rs, ps, null, conn);
        }

        return resultList;

    }

    /**
     * 利用反射和数据库元数据把结果集里的数据封装为对应的类
     *
     * @param clazz
     * @param resultList
     * @param rs
     * @param colNames
     * @param methods
     * @param <T>
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws SQLException
     */
    private <T> void constructResultObjects(Class<T> clazz, List<T> resultList, ResultSet rs, String[] colNames,
                                            Method[] methods) throws InstantiationException, IllegalAccessException, InvocationTargetException,
            SQLException {
        String prefixMethodName = "set";
        // 实例化对象
        T clazzObject = (T) clazz.newInstance();

        for (int i = 0; i < colNames.length; i++) {
            for (Method method : methods) {
                String methodName = method.getName();

                if (Modifier.isPublic(method.getModifiers()) && methodName.startsWith(prefixMethodName)) {
                    String columnLabel = colNames[i];
                    // 构造标准JavaBean的set方法签名
                    String invokeMethodName = prefixMethodName.concat(columnLabel.substring(0, 1).toUpperCase()
                            .concat(columnLabel.substring(1)));
                    if (invokeMethodName.equals(methodName)) {
                        // 此处的方法调用的参数赋值存在这种情况：
                        // 当result读取的结果为null但方法参数类型为基本类型时会出现java.lang.IllegalArgumentException
                        // TODO
                        // 这种情况极为复杂，暂滞留问题[初步解决方案为：1、属性类型都有封装类型。2、进了不要出现空值结果]
                        method.invoke(clazzObject, rs.getObject(columnLabel));
                        break;
                    }
                }
            }
        }
        resultList.add(clazzObject);
    }

    public void close(ResultSet rs, PreparedStatement ps, Statement st, Connection conn) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != st) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != conn) {
            this.myDataSource.free(conn);
        }
    }
}
