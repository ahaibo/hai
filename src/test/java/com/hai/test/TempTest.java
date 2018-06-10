/**
 *
 */
package test;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Administrator
 */
public class TempTest {

    @Test
    public void test() {
        System.out.println(Long.MAX_VALUE);
    }

    public static void main(String[] args) {
    }

    @Test
    public void testProperties() {
        Properties properties = System.getProperties();
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
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
