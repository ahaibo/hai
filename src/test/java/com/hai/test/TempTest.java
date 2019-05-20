/**
 *
 */
package test;

import org.apache.groovy.util.Maps;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class TempTest {

    @Test
    public void test() {
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

}
