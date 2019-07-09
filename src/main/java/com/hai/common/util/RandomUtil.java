package com.hai.common.util;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Administrator
 */
public class RandomUtil {
    /** 最小整数 */
    private static final int DEFATULT_MIN_NUM = 1;
    /** 最小整百数 */
    private static final int DEFATULT_MIN_NUM_BY_HUNDRED = 100;
    /** 最大整数 */
    private static final int DEFATULT_MAX_NUM = Integer.MAX_VALUE;
    public static final String NUMBER_CHARACTERS = "123456789";
    public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALL_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    public static final String EMALS[] = {"@163.com", "@sina.com", "@sohu.com", "@gmail.com"};
    public static final int CHARACTERS_LENGTH = CHARACTERS.length();
    public static final int ALL_CHARACTERS_LENGTH = ALL_CHARACTERS.length();
    public static final int NUMBER_CHARACTERS_LENGTH = NUMBER_CHARACTERS.length();
    public static final int EMALS_LENGTH = EMALS.length;
    static final String CHAR_CACHES[] =
            {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 产生整百的整数
     *
     * @param minNum
     * @param maxNum
     * @return
     */
    public static int randomHundredInteger(int minNum, int maxNum) {
        return random(minNum, maxNum, true, false, false, false);
    }

    /**
     * 此方法默认产生自然数1及以上的数据
     *
     * @param minNum
     * @param maxNum
     * @return
     */
    public static int random(int minNum, int maxNum) {
        Random random = new Random();
        int result = 0;
//        do {
//            result = random.nextInt(maxNum);
//        } while (result < minNum);

        result = random.nextInt(maxNum);

        return result < minNum ? minNum + result : result;
    }

    public static float random(float minNum, float maxNum) {
        Random random = new Random();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        float multiplier = maxNum / 2;
        float result = 0;
        do {
            result = random.nextFloat() * multiplier;
        } while (result < minNum || result > maxNum);

        return Float.valueOf(numberFormat.format(result));
    }

    public static double random(double minNum, double maxNum) {
        Random random = new Random();
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        double multiplier = maxNum / 2;
        double result = 0;
        do {
            result = random.nextDouble() * multiplier;
        } while (result < minNum || result > maxNum);

        return Double.valueOf(numberFormat.format(result));
    }

    public static void main(String[] args) {
        // 取整百
        // System.out.println(12345 / 100 * 100);

        for (int i = 0; i < 100; i++) {
            // System.out.println(random(500f, 9999.5f));
            // System.out.println(randomBoolean());
            System.out.println(randomInt(15));
        }

    }

    /**
     * 生成指定区间的随机数
     *
     * @param minNum
     * @param maxNum
     * @param isMinNumAny
     * @param isMinNegative
     * @param isMinZero
     * @return
     */
    public static int random(int minNum, int maxNum, boolean isHundredInteger, boolean isMinNumAny, boolean isMinNegative, boolean isMinZero) {
        if (isHundredInteger) {
            // 如果要求产生整百数，则首先不能为小于等于0的数，其次防止传入的最小值和最大值不是整百数时需手动转换
            minNum = minNum < 0 ? DEFATULT_MIN_NUM_BY_HUNDRED : minNum / DEFATULT_MIN_NUM_BY_HUNDRED * DEFATULT_MIN_NUM_BY_HUNDRED;
        } else {
            minNum = checkMinNum(minNum, isMinNumAny, isMinNegative, isMinZero);
        }

        if (maxNum <= 0 || maxNum <= minNum) {
            maxNum = DEFATULT_MAX_NUM;
        }

        Random random = new Random();
        int result = 0;
        do {
            result = random.nextInt(maxNum);
        } while (result < minNum);

        return isHundredInteger ? result / DEFATULT_MIN_NUM_BY_HUNDRED * DEFATULT_MIN_NUM_BY_HUNDRED : result;
    }

    /**
     * @param minNum
     * @param isMinNumAny
     * @param isMinNegative
     * @param isMinZero
     * @return
     */
    private static int checkMinNum(int minNum, boolean isMinNumAny, boolean isMinNegative, boolean isMinZero) {
        if (!isMinNumAny) {
            if (!isMinNegative && minNum < 0) {
                minNum = DEFATULT_MIN_NUM;
            }

            if (!isMinZero) {
                minNum = DEFATULT_MIN_NUM;
            }
        }

        return minNum;
    }

    public static boolean randomBoolean() {
        Random random = new Random();

        int num = random.nextInt(2);
        boolean result = num == 0 ? true : false;

        return result;
    }

    public static int randomInt(int size) {
        return new Random().nextInt(size);
    }


    enum StringType {
        NORMAL, UPPER, LOWER
    }

    @Test
    public void test() {
        System.out.println(randomString());
        System.out.println(randomString(StringType.UPPER));
        System.out.println(randomString(StringType.LOWER, 128));
    }

    public static String randomString() {
        return randomString(StringType.NORMAL);
    }

    public static String randomString(int length) {
        return randomString(StringType.NORMAL, length);
    }

    public static String randomString(StringType type) {
        return randomString(type, new Random().nextInt(64) + 1);
    }

    public static String randomString(StringType type, int length) {
        String str = null;
        int len = CHAR_CACHES.length - 1;
        Random random = new Random();
        StringBuilder s = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            s.append(CHAR_CACHES[random.nextInt(len) + 1]);
        }
        str = s.toString();
        switch (type) {
            case UPPER:
                str = str.toUpperCase();
                break;
            case LOWER:
                str = str.toLowerCase();
                break;
        }
        return str;
    }

    public static String randomRangeCharacters(int min, int max) {
        Integer[] range = getRangeNumbers(min, max);
        return randomCharacters(range[randomNumber(range.length)]);
    }

    public static String randomCharacters(int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(CHARACTERS.charAt(randomNumber(CHARACTERS_LENGTH)));
        }
        return str.toString();
    }

    public static String randomNumbers(int length) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(NUMBER_CHARACTERS.charAt(randomNumber(NUMBER_CHARACTERS_LENGTH)));
        }
        return str.toString();
    }

    public static String randomMobile() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            str.append(randomNumber(10));
        }
        return 1 + str.toString();
    }

    public static String randomEmail() {
        int[] range = {5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
        String email = EMALS[randomNumber(EMALS_LENGTH)];
        return randomCharacters(range[randomNumber(range.length)]) + email;
    }

    public static int randomNumber(int range) {
        return new Random().nextInt(range);
    }

    public static int randomNumber(int range, int min) {
        int num = new Random().nextInt(range);
        return num < min ? min + num : num;
    }

    public static int randomNumber(int range, int min, int max) {
        if (min > max) {
            max = min;
        }
        int num = new Random().nextInt(range);
        return num < min ? min + num : num > max ? max : num;
    }

    public static Integer[] getRangeNumbers(int min, int max) {
        List<Integer> list = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            list.add(i);
        }
        return list.toArray(new Integer[list.size()]);
    }
}
