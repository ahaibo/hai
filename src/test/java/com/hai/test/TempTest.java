/**
 *
 */
package test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.groovy.util.Maps;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class TempTest {

    @Test
    public void test() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

    public static void main(String[] args) {
        testRandomNumber();
    }

    @Test
    public void testProperties() {
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Test
    public void testStreamFilter() {
        List<Map<String, Integer>> list = new ArrayList<>();
        list.add(Maps.of("a", 1));
        list.add(Maps.of("a", 1, "b", 2));
        list.add(Maps.of("a", 1, "b", 2, "c", 3));
        list.add(Maps.of("a", 1, "b", 2, "c", 3));
        list.add(Maps.of("a", 1, "b", 2, "c", 3));
        list.add(Maps.of("a", 1, "b", 2, "c", 3));
        list.add(Maps.of("a", 1, "b", 2, "c", 3));
        list.add(Maps.of("a", 1, "b", 2, "c", 3));
        list.add(Maps.of("a", 1, "b", 2, "c", 3));

        System.out.println(list.size());

        List<Map<String, Integer>> list2 = list.stream().filter(map -> map.size() > 1).collect(Collectors.toList());
        list = list.stream().filter(map -> map.size() > 1).collect(Collectors.toList());

        System.out.println(list2.size());
        System.out.println(list.size());
    }

    @Test
    public void testOptional() {
        List<String> aa = new ArrayList();
        aa.add("aaa");
        aa.add("abbb");
        aa.add("accc");
        aa.add("ddd");
        System.out.println("原始值：" + aa);


        Optional<String> largest = aa.stream().max(String::compareToIgnoreCase);

        List<String> bb = new ArrayList();
        largest.ifPresent(bb::add);

        System.out.println("ifPresent 的用法：" + bb);


        Optional<Boolean> added = largest.map(bb::add);
        System.out.println("会有返回值处理:" + added.get());

    }


    public static void testRandomNumber() {
        // System.out.println(diGui(50));
        long start = System.currentTimeMillis();
        int len = 100000;
        Object[] arr = new Object[len];
        // Random random = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 99999999);
            // System.out.println(arr[i]);
        }

        // for (int i = 0; i < len; i++)
        // {
        // arr[i] = UUID.randomUUID().toString();
        // }

        long curr = System.currentTimeMillis();
        System.out.println("timer: " + (curr - start));
    }

    /**
     * @param i
     */
    private static int diGui(int i) {
        if (i > 0) {
            return i + diGui(i - 1);
        }
        return i;
    }

    @Test
    public void test2() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String betNumber = "特码@19,18,17,21,22,23,24,25,26,47,48";
            betNumber.split("@");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    @Test
    public void updateUserType() {
        for (int i = 0; i < 100; i++) {
            String suffix = i + "";
            if (i < 10) {
                suffix = "0" + suffix;
            }
            System.out.println("update app_member set user_type=5 where account='ceshi" + suffix + "';");
        }
    }

    @Test
    public void updateVipGrade() {
//        String filePath = "C:\\Users\\Administrator\\Documents\\xgc.id.txt";
        String filePath = "D:\\hai\\Notes\\account\\lht-id.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println("update app_member set vip_id=5 where account='" + line + "';");
//                System.out.println("update app_member set user_type=5 where account='" + line + "';");
//                System.out.print("" + line + ",");
                System.out.print("APP_MEMBER_" + line + ",");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void clearMemberCache() {
        String filePath = "D:\\hai\\Notes\\account\\lht-account.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.print("'" + line + "',");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test3() {
        for (int i = 0; i < 100000; i++) {
            long time1 = System.currentTimeMillis(), time2 = time1 / 1000;
            System.out.print(time1);
            System.out.print(",");
            System.out.print(time2);
            System.out.println();
        }
    }

    @Test
    public void test4() {
        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new String[]{"管理员账号" + i, "客户账号" + i, "客户账号" + i});
        }

        System.out.println(JSONObject.toJSONString(list));
    }
}
