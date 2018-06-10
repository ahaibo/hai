package com.hai.db.nosql.cassandra;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
public class CRUDTest extends BaseCassandraTest {

    @Test
    public void insert() {
        Insert insert = QueryBuilder.insertInto("mydb", "test");
        insert.values(new String[]{"a", "b", "c", "d"}, new Object[]{"a4", 4, "b4", 5});
        ResultSet resultSet = session.execute(insert);
        List<Row> rows = resultSet.all();
        for (Row row : rows) {
            System.out.println("result: " + row.getInet(0));
        }
    }

    @Test
    public void selectAll() {
        Select select = QueryBuilder.select().all().from("mydb", "test");
        ResultSet resultSet = session.execute(select);
        List<Row> rows = resultSet.all();
        for (Row row : rows) {
            System.out.println(ToStringBuilder.reflectionToString(row, ToStringStyle.JSON_STYLE));
        }
    }

    @Test
    public void select() {
        Select.Where where = QueryBuilder.select().from("mydb", "test").where(QueryBuilder.eq("a", "a1"));
        ResultSet resultSet = session.execute(where);
        List<Row> rows = resultSet.all();
        for (Row row : rows) {
            System.out.println(ToStringBuilder.reflectionToString(row, ToStringStyle.JSON_STYLE));
        }
    }

    @Test
    public void update() {
        Update.Where where = QueryBuilder.update("mydb", "test")
                .with(QueryBuilder.set("d", 1))
                .where(QueryBuilder.eq("a", "a1"))
                .and(QueryBuilder.eq("b", 1));

        ResultSet resultSet = session.execute(where);
        List<Row> rows = resultSet.all();
        for (Row row : rows) {
            System.out.println(ToStringBuilder.reflectionToString(row, ToStringStyle.JSON_STYLE));
        }
    }

    @Test
    public void delete() {
        Delete.Where where = QueryBuilder.delete("d").from("mydb", "test")
                .where(QueryBuilder.eq("a", "a4"))
                .and(QueryBuilder.eq("b", 4));

        ResultSet resultSet = session.execute(where);
        List<Row> rows = resultSet.all();
        for (Row row : rows) {
            System.out.println(ToStringBuilder.reflectionToString(row, ToStringStyle.JSON_STYLE));
        }
    }

    @Test
    public void parare() {
        String sql = "insert into mydb.test(a,b,c,d) values(?,?,?,?)";
        PreparedStatement statement = session.prepare(sql);
        BoundStatement bound = statement.bind()
//                .bind("a5",5,"b5",5)
                .setString(0, "a5")
                .setInt(1, 5)
                .setString(2, "b5")
                .setInt(3, 5);

        ResultSet resultSet = session.execute(bound);
        List<Row> rows = resultSet.all();
        for (Row row : rows) {
            System.out.println(ToStringBuilder.reflectionToString(row, ToStringStyle.JSON_STYLE));
        }
    }

    @Test
    public void parare2() {
        String sql = "insert into mydb.test(a,b,c,d) values(:a,:b,:c,:d)";
        PreparedStatement statement = session.prepare(sql);
        BoundStatement bound = statement.bind()
//                .bind("a5",5,"b5",5)
                .setString("a", "a6")
                .setInt("b", 6)
                .setString("c", "b6")
                .setInt("d", 6);

        ResultSet resultSet = session.execute(bound);
        List<Row> rows = resultSet.all();
        for (Row row : rows) {
            System.out.println(ToStringBuilder.reflectionToString(row, ToStringStyle.JSON_STYLE));
        }
    }
}
