/**
 *
 */
package test.mysql;

import com.hai.common.db.DBUtil;
import com.hai.common.util.RandomUtil;

import java.io.IOException;
import java.util.Date;


/**
 * @author Administrator
 */
public class MysqlDBTest {
    public static void main(String[] args) throws IOException {
        String propFilePath = "properties/MysqlDB.properties";
        String sql = "insert into user(name,sex,age,email,birthday,money) values(?,?,?,?,?,?)";
        int age = RandomUtil.random(18, 40);
        float money = RandomUtil.random(1000f, 100 * 10000f);
        Object[] params = {"lisi", "M", age, "testemail" + age + "@sohu.com", new Date(), money};

        int result = DBUtil.newInstance(propFilePath).update(sql, params);

        System.out.println("DBBase.newInstance(propFilePath).update(sql, params) result:\t" + result);
    }
}
