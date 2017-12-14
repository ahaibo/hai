/**
 *
 */
package com.hai.ijavase.iservlet.iusermanager.common;

import com.hai.common.db.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * @author Administrator
 */
public class Page {
    private int pageNo;
    private int pageSize;
    private int pageCount;
    private int rowCount;
    private int minPageSize;
    private int maxPageSize;
    public static final int DEFAULT_MIN_PAGE_SIZE = 2;
    public static final int DEFAULT_MAX_PAGE_SIZE = 20;
    public static final int DEFAULT_PAGE_NO = 1;
    public static final int DEFAULT_PAGE_SIZE = 5;

    public Page() {
    }

    public Page(DBUtil dbBase, String tableName) {
        this.pageNo = DEFAULT_PAGE_NO;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.minPageSize = 2;
        this.maxPageSize = 20;
        this.setPageCount(getPageCount(dbBase, tableName));
    }

    /**
     * @param pageNo
     * @param pageSize
     * @param minPageSize
     * @param maxPageSize
     */
    public Page(int pageNo, int pageSize, int minPageSize, int maxPageSize) {
        this.pageNo = pageNo;
        this.setPageSize(pageSize);
        this.setMinPageSize(minPageSize);
        this.setMaxPageSize(maxPageSize);
    }

    public Page(int pageNo, int pageSize, int minPageSize, int maxPageSize, int pageCount) {
        this(pageNo, pageSize, minPageSize, maxPageSize);
        this.pageCount = pageCount;
    }

    public Page(int pageNo, int pageSize, int minPageSize, int maxPageSize, int pageCount, int rowCount) {
        this(pageNo, pageSize, minPageSize, maxPageSize, pageCount);
        this.rowCount = rowCount;
    }

    /**
     * 查询按当前页记录数大小共有多少页
     *
     * @param dbBase
     * @param tableName
     * @return
     */
    public static int getPageCount(DBUtil dbBase, String tableName) {
        return getPageCount(dbBase, tableName, DEFAULT_PAGE_SIZE);
    }

    /**
     * 查询按当前页记录数大小共有多少页
     *
     * @param dbBase
     * @param tableName
     * @param pageSize
     * @return
     */
    public static int getPageCount(DBUtil dbBase, String tableName, int pageSize) {
        ResultSet resultSet = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        int pageCount = 0;
        int rowCount = 0;
        pageSize = pageSize < DEFAULT_MIN_PAGE_SIZE ? DEFAULT_PAGE_SIZE
                : pageSize > DEFAULT_MAX_PAGE_SIZE ? DEFAULT_MAX_PAGE_SIZE : pageSize;

        String sql = "select count(*) from ".concat(tableName);

        try {
            Map<String, Object> resultMap = dbBase.query(sql);
            if (null == resultMap || resultMap.isEmpty()) {
                return pageCount;
            }

            resultSet = (ResultSet) resultMap.get("resultSet");
            if (null == resultSet) {
                return pageCount;
            }

            statement = (Statement) resultMap.get("statement");
            preparedStatement = (PreparedStatement) resultMap.get("preparedStatement");
            connection = (Connection) resultMap.get("connection");

            resultSet.first();
            rowCount = resultSet.getInt(1);

            // 计算pageCount
            // 一种最简单的pageCount的计算方式
            pageCount = (rowCount - 1) / pageSize + 1;

            // 计算pageCount原理
            // pageCount = pageCount % page.getPageSize() == 0 ? pageCount /
            // page.getPageSize() : pageCount / page.getPageSize() + 1;

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            dbBase.closeAll(resultSet, preparedStatement, statement, connection);
        }

        return pageCount;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo < DEFAULT_PAGE_NO ? DEFAULT_PAGE_NO
                : (this.pageCount > 0 ? (pageNo > this.pageCount ? this.pageCount : pageNo) : pageNo);
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize > DEFAULT_MAX_PAGE_SIZE ? DEFAULT_MAX_PAGE_SIZE
                : pageSize < DEFAULT_MIN_PAGE_SIZE ? DEFAULT_MIN_PAGE_SIZE : pageSize;
    }

    /**
     * @return the pageCount
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * @param pageCount the pageCount to set
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * @return the minPageSize
     */
    public int getMinPageSize() {
        return minPageSize;
    }

    /**
     * @param minPageSize the minPageSize to set
     */
    public void setMinPageSize(int minPageSize) {
        this.minPageSize = minPageSize < DEFAULT_MIN_PAGE_SIZE ? DEFAULT_MIN_PAGE_SIZE
                : minPageSize > DEFAULT_MAX_PAGE_SIZE ? DEFAULT_MAX_PAGE_SIZE : minPageSize;
    }

    /**
     * @return the maxPageSize
     */
    public int getMaxPageSize() {
        return maxPageSize;
    }

    /**
     * @param maxPageSize the maxPageSize to set
     */
    public void setMaxPageSize(int maxPageSize) {
        this.maxPageSize = maxPageSize > DEFAULT_MAX_PAGE_SIZE ? DEFAULT_MAX_PAGE_SIZE
                : maxPageSize < DEFAULT_MIN_PAGE_SIZE ? DEFAULT_MIN_PAGE_SIZE : maxPageSize;
    }

    /**
     * @return the rowCount
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @param rowCount the rowCount to set
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public static Page newInstance() {
        return new Page();
    }

    public static Page newInstance(DBUtil dbBase, String tableName) {
        return new Page(dbBase, tableName);
    }
}
