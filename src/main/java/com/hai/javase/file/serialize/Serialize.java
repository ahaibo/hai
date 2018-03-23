/**
 *
 */
package com.hai.javase.file.serialize;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * @author Administrator
 */
public class Serialize {
    static Random random = new Random();
    static int bound = 30000;

    public static void main(String[] args) {

        Salary salary = new Salary(random.nextInt(bound), random.nextInt(20000));
        Person person = new Person(UUID.randomUUID().toString(), randomName(), randomSex(), random.nextInt(100), salary);

        SerializationUtils.writeObject(person);
        System.out.println("serialize over...");

        Person p = (Person) SerializationUtils.readObject();
        System.out.println("deserialize over");
        System.out.println(JSONObject.toJSONString(p, true));
    }

    private static String randomSex() {
        return random.nextInt(2) == 0 ? "WOMEN" : "MAN";
    }

    public static String randomName() {
        String name = null;
        switch (random.nextInt(6)) {
            case 0:
                name = "ZhangSan";
                break;
            case 1:
                name = "LiSi";
                break;
            case 2:
                name = "WangWu";
                break;
            case 3:
                name = "Jackie";
                break;
            case 4:
                name = "Jet li";
                break;
            case 5:
                name = "hai";
                break;
        }
        return name;
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(3));
        }
    }
}
