package com.hai.db.nosql.cassandra;

import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import org.junit.Test;

/**
 * Created by Administrator on 2018/5/7.
 */
public class KeyspaceTest extends BaseCassandraTest {

    @Test
    public void createKeyspace() {
        // 单数据中心 复制策略 ：1
        String cql = "CREATE KEYSPACE if not exists mydb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}";
        session.execute(cql);
    }

    @Test
    public void createTable() {
        // a,b为复合主键 a：分区键，b：集群键
        String cql = "CREATE TABLE if not exists mydb.test (a text,b int,c text,d int,PRIMARY KEY (a, b))";
        session.execute(cql);
    }

    @Test
    public void insert() {
        String sql = "insert into mydb.test(a,b,c,d) values('a2',4,'c2',6)";
        session.execute(sql);
    }

    @Test
    public void update() {
        // a,b是复合主键 所以条件都要带上，少一个都会报错，而且update不能修改主键的值，这应该和cassandra的存储方式有关
        String cql = "UPDATE mydb.test SET d = 1234 WHERE a='aa' and b=2;";
        // 也可以这样 cassandra插入的数据如果主键已经存在，其实就是更新操作
        String cql2 = "INSERT INTO mydb.test (a,b,d) VALUES ( 'aa',2,1234);";
        // cql 和 cql2 的执行效果其实是一样的
        session.execute(cql);
    }

    @Test
    public void delete() {
        // 删除一条记录里的单个字段 只能删除非主键，且要带上主键条件
        String cql = "DELETE d FROM mydb.test WHERE a='aa' AND b=2;";
        // 删除一张表里的一条或多条记录 条件里必须带上分区键
        String cql2 = "DELETE FROM mydb.test WHERE a='aa';";
        session.execute(cql);
        session.execute(cql2);
    }

    @Test
    public void query() {
        String cql = "SELECT * FROM mydb.test;";
        String cql2 = "SELECT a,b,c,d FROM mydb.test;";

        ResultSet resultSet = session.execute(cql);
        System.out.print("字段名：\n");
        for (ColumnDefinitions.Definition definition : resultSet.getColumnDefinitions()) {
            System.out.print(definition.getName() + "\t");
        }
        System.out.println();
        System.out.println("--------------------------------------------");
        for (Row row : resultSet) {
            System.out.println(
                    String.format(
                            "%s\t%d\t%s\t%d\t",
                            row.getString("a"),
                            row.getInt("b"),
                            row.getString("c"),
                            row.getInt("d")));
        }
    }

    public void query2(){

    }
}
