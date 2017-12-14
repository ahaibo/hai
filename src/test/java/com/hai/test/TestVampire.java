package com.hai.test;

import java.util.HashSet;
import java.util.Set;

/**
 * 吸血鬼数字：吸血鬼数字是指位数为偶数的数字，可以由一对数字相乘而得到，而这对数字各包含乘积的一半位数的数字，其中从最初的数字中选取的数字可以任意排序。
 * 写一个程序，找出4位数的所有吸血鬼的数字
 * 例如：1260=21*60
 * 1827=21*87
 * Created by Administrator on 2017/5/2.
 */
public class TestVampire {
    static int count;
    static Set<Integer> vampireNums = new HashSet<>();

    public static void main(String[] args) {
        testVampire();
        testVampireThinkinginJava();
        testVampireNumbersLJ();
        testVampireFromInternet();
    }

    public static void testVampireThinkinginJava() {
        SearchforVampireThinkinginJava.main(null);
    }

    public static void testVampireNumbersLJ() {
        SearchforVampireNumbersLJ.main(null);
    }

    public static void testVampireFromInternet() {
        SearchforVampireFromInternet.main(null);
    }

    public static void testVampire() {
        for (int num = 1001; num < 10000; num++) {
            math(num);
        }
        System.out.println(TestVampire.class.getName() + "\n\tcount: " + count + "\n");
    }

    public static void math(int num) {
        int[] temp1 = new int[2];
        int[] temp2 = new int[2];

        int a = num / 1000;
        int b = num / 100 % 10;
        int c = num / 10 % 10;
        int d = num % 10;
        int[] data = {a, b, c, d};
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (i == j) {
                    continue;
                }
                temp1[0] = data[i];
                temp1[1] = data[j];
                for (int m = 0; m < data.length; m++) {
                    if (m != i && m != j) {
                        temp2[0] = data[m];
                        for (int n = 0; n < data.length; n++) {
                            if (n != i && n != j && n != m) {
                                temp2[1] = data[n];
                                multi(data, temp1, temp2);
                            }
                        }
                    }
                }
            }
        }
    }

    public static int toInt(int[] temp) {
        int m = 0;
        int[] temp1 = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            temp1[i] = temp[i] * (int) Math.pow(10, temp.length - 1 - i);
        }
        for (int i = 0; i < temp1.length; i++) {
            m += temp1[i];
        }
        return m;
    }

    public static void multi(int[] temp, int[] temp1, int[] temp2) {
        int i = toInt(temp1);
        int j = toInt(temp2);
        int k = toInt(temp);
        if (k == i * j && !vampireNums.contains(k)) {
            System.out.println(k + "=" + i + "*" + j);
            vampireNums.add(k);
        }
        count++;
    }
}

/**
 * 效率略低 ThinkInJava 提供版
 */
class SearchforVampireThinkinginJava {

    // control/VampireNumbers.java
    // TIJ4 Chapter Control, Exercise 10, page 154
    /* A vampire number has an even number of digits and is formed by multiplying a
    * pair of numbers containing half the number of digits of the result. The
    * digits are taken from the original number in any order. Pairs of trailing
    * zeroes are not allowed. Examples include: 1260 = 21 * 60, 1827 = 21 * 87,
    * 2187 = 27  * 81. Write a program that finds all the 4-digit vampire numbers.
    * (Suggested by Dan Forhan.)
    */
    //  本方法是顺向思维，即先有四位数，再拆分，四个数字组合相乘，若乘积与原数相等，则输出，并计算为一个吸血鬼数字。TMJG添加此行并注释
    //  其实sum的结果为107976次，非常大，算法效率很低，并且出现了重复（6880 = 86 * 80，6880 = 80 * 86）。TMJG添加此行并注释

    static int sum = 0;//记录调用判断的次数,TMJG添加此行并注释

    static int a(int i) {
        return i / 1000;    //求千位数字，下同,TMJG添加此行并注释
    }

    static int b(int i) {
        return (i % 1000) / 100;
    }

    static int c(int i) {
        return ((i % 1000) % 100) / 10;
    }

    static int d(int i) {
        return ((i % 1000) % 100) % 10;
    }

    static int com(int i, int j) {   //形成10~99的两位数,TMJG添加此行并注释
        return (i * 10) + j;
    }

    static void productTest(int i, int m, int n) {
        sum++;
        if (m * n == i) System.out.println(i + " = " + m + " * " + n);
    }

    public static void main(String[] args) {
        for (int i = 1001; i < 9999; i++) {
            productTest(i, com(a(i), b(i)), com(c(i), d(i)));
            productTest(i, com(a(i), b(i)), com(d(i), c(i)));
            productTest(i, com(a(i), c(i)), com(b(i), d(i)));
            productTest(i, com(a(i), c(i)), com(d(i), b(i)));
            productTest(i, com(a(i), d(i)), com(b(i), c(i)));
            productTest(i, com(a(i), d(i)), com(c(i), b(i)));
            productTest(i, com(b(i), a(i)), com(c(i), d(i)));
            productTest(i, com(b(i), a(i)), com(d(i), c(i)));
            productTest(i, com(b(i), c(i)), com(d(i), a(i)));
            productTest(i, com(b(i), d(i)), com(c(i), a(i)));
            productTest(i, com(c(i), a(i)), com(d(i), b(i)));
            productTest(i, com(c(i), b(i)), com(d(i), a(i)));
        }
        System.out.println(SearchforVampireThinkinginJava.class.getName() + "\n\t总共调用判断的次数为：" + sum + "\n");//TMJG添加此行并注释
    }
}

/**
 * 本方法是对方法一的改进，跳过了一些数字（如1100这样末尾两个0的，如1010、1001这样明显不可能是吸血鬼数字的数字）
 * 并且避免了出现重复的可能性，但是效率仍然很低
 */
class SearchforVampireNumbersLJ {

    public static int count = 0;// 记录一共有多少个吸血鬼数字
    public static int k = 0;//记录调用判断多少次

    public static void main(String[] args) {
        for (int i = 1001; i < 10000; i++) {
            // 如果数字是像1100这种末尾至少有2个0的，则跳过
            if (i % 100 == 0) {
                continue;
            }
            // 获得数字四个数值位上的数字,这里我们假定四位数表示为abcd
            int a = i / 1000;
            int b = (i - a * 1000) / 100;
            int c = (i - a * 1000 - b * 100) / 10;
            int d = i - a * 1000 - b * 100 - c * 10;
            // 判断四个位置上是否有两个0存在的情况，如1010，并跳过这些数，由于千位不可能为0，因此只需要判断另外三位是否有2个0的情况
            // 当3个数中有2个0时，必然存在“3个数之和等于其中某一个数”，以此作为判断依据，而后两位为0的也已经排除，其实只需要判断如1001，和1010这种情况即可
            if (b + c + d == c || b + c + d == d) {
                continue;
            }
            // 排除掉各种情况后，可以开始真正的吸血鬼数字筛选了
            // 那么一共有12种情况：abcd,abdc,acbd,acdb,adbc,adcb,bacd,badc,bcda,bdca,cadb,cbda
            if (search(i, a, b, c, d)) {
            } else if (search(i, a, b, d, c)) {
            } else if (search(i, a, c, b, d)) {
            } else if (search(i, a, c, d, b)) {
            } else if (search(i, a, d, b, c)) {
            } else if (search(i, a, d, c, b)) {
            } else if (search(i, b, a, c, d)) {
            } else if (search(i, b, a, d, c)) {
            } else if (search(i, b, c, d, a)) {
            } else if (search(i, b, d, c, a)) {
            } else if (search(i, c, a, d, b)) {
            } else if (search(i, c, b, d, a)) {
            }
        }
        System.out.println(SearchforVampireNumbersLJ.class.getName() + " result:");
        System.out.println("\t四位数的吸血鬼数字一共有" + count + "个。");
        System.out.println("\t一共调用判断次数为" + k + "\n");
    }

    //判断是否满足条件
    static boolean search(int i, int a, int b, int c, int d) {
        k++;
        if ((a * 10 + b) * (c * 10 + d) == i) {
            searchfor(i, a, b, c, d);//如果满足条件，则打印结果
            return true;
        } else {
            return false;
        }
    }

    //满足条件即打印，并且统计个数
    static void searchfor(int i, int a, int b, int c, int d) {
        count++;
        System.out.println(i + "=" + (a * 10 + b) + "*" + (c * 10 + d));
    }
}

/**
 * 该算法采用逆向思维，4位数字的吸血鬼数字只能拆分成两个2位数，因此遍历所有两个两位数相乘的情况，除去不符合的情况不用判断，其他挨个判断即可。
 */
class SearchforVampireFromInternet {
    /**
     * 代码来自网络，略作修改并添加了注释
     * 该算法只需要执行3721次
     */
    public static void main(String[] args) {
        String[] targetNum = null;
        String[] gunNum = null;   //目标数字和枪数字（即对比数字）
        int sum = 0;   //吸血鬼数字的总个数
        int count = 0;   //有效判断次数，那些乘积不是4位数的就排除了
        for (int i = 10; i < 100; i++) {
            for (int j = i + 1; j < 100; j++) {   //没有哪个两位数满足ab*ab=abab（不信可以编程验证），所以这里j从i+1开始就可以了
                int i_target = i * j;
                if (i_target < 1000 || i_target > 9999)
                    continue; // 积不是4位数则跳过
                count++;
                targetNum = String.valueOf(i_target).split("");  //将其转换为一个字符串数组
                gunNum = (String.valueOf(i) + String.valueOf(j)).split("");
                java.util.Arrays.sort(targetNum);   //升序排列，因为只有排列了再比较才能保证不遗漏abcd=ba*dc这样的情况
                java.util.Arrays.sort(gunNum);
                if (java.util.Arrays.equals(targetNum, gunNum)) {
                    // 排序后比较,为真则找到一组
                    sum++;
                    System.out.println("第" + sum + "个: " + i_target + "=" + i + "*" + j);
                }
            }
        }
        System.out.println(SearchforVampireFromInternet.class.getName() + "\n\t共进行了" + count + "次判断，找到" + sum + "个吸血鬼数字。");
    }
}
