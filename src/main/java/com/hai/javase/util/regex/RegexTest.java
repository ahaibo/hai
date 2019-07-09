/**
 *
 */
package com.hai.javase.util.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
public class RegexTest {

    public static void main(String[] args) {
        String str = "a              b  c";
        String regex = " +";// 按多个空格进行切割
        // splitTest(str, regex);

        str = "d:\\a\\b\\c.txt";
        regex = "\\\\";// 反斜杠
        // splitTest(str, regex);

        str = "abbbbbbabccddefggghi";// 叠词分割
        regex = "(.)\\1";// (.) 代表任意字符的组，\\1 匹配一个叠词
        splitTest(str, regex);

        regex = "(.)\\1+";// (.) 代表任意字符的组，\\1+ 匹配多个叠词
        splitTest(str, regex);

        // 将多余5位数字的数值替换成#
        String repStr = "aa1234567890def5234557afja2323efasdf3524345767fa";
        String repReg = "\\d{5,}";
        String replacement = "#";
        replaceAllTest(repStr, repReg, replacement);

        // 将叠词替换成&
        repStr = "aaaaaaaaaaaaaaaaabcccccccccdefgabcdfaldfajssfasafjadafasfdefasdfadfa";
        repReg = "(.)\\1";// 匹配替换单个叠词
        replacement = "&";
        replaceAllTest(repStr, repReg, replacement);

        repReg = "(.)\\1+";// 匹配替换多个叠词
        replaceAllTest(repStr, repReg, replacement);

        // 把叠词替换为相应的单个字符
        repReg = "(.)\\1+";
        replacement = "$1";// $是正则表达式的特殊字符，表示获取前面的组的值
        replaceAllTest(repStr, repReg, replacement);

        // java正则表达式的应用

        String regexStr = "my regex test le gh wr gou hai a !";
        String rgx = "\\b[a-zA-Z]{2}\\b";// 查找两个字母的单词
        regex(regexStr, rgx);

        // 正则表达式练习
        regexTest1();

        // 正则表达式练习：对ip段进行排序
        String ipStrs = "127.1.2.3 2.11.2.43 1.1.2.3   213.0.211.123  217.12.23.34     2.11.1.34 ";
        String[] ipStrArr = {"127.1.2.3 ", "2.11.2.43 ", "1.1.2.3   ", "213.0.211.123  ", "217.12.23.34     ",
                "2.11.1.34 "};
        ipSort(ipStrs, true);

        String email = "_ahai@qq.org.com.cn";
        System.out.println(checkEmail(email));
    }

    /**
     * 邮箱的正则校验
     *
     * @param email
     */
    private static boolean checkEmail(String email) {
        // 如下规则表示：邮箱@前部分必须为4-12位字符且必须以_或字母开头，@之后.之前至少有两位字符或数字，域名[.XX]至多3为至少一位
        String regex = "^[a-zA-Z_]\\w{3,11}@[a-z0-9]{2,}(\\.[a-zA-Z]{2,}){1,3}$";

        return email.matches(regex);
    }

    /**
     * ip排序
     *
     * @param ipStrs 要排序的ip字符数组和单个字符串【当为单个字符串时个ip之间用空格分隔】
     * @param isAsc  是否升序排序，默认是
     */
    private static String[] ipSort(String ipStrs, boolean isAsc) {
        return ipSort(isAsc, ipStrs);
    }

    /**
     * ip排序
     *
     * @param ipStr 要排序的ip字符数组和单个字符串【当为单个字符串时个ip之间用空格分隔】
     */
    private static String[] ipSort(String... ipStr) {
        return ipSort(true, ipStr);
    }

    /**
     * ip排序
     *
     * @param isAsc    是否升序排序，默认是
     * @param ipStrArr 要排序的ip字符数组和单个字符串【当为单个字符串时个ip之间用空格分隔】
     */
    private static String[] ipSort(String[] ipStrArr, boolean isAsc) {
        return ipSort(isAsc, ipStrArr);
    }

    /**
     * ip排序
     *
     * @param isAsc  是否升序排序，默认是
     * @param ipStrs 要排序的ip字符数组和单个字符串【当为单个字符串时个ip之间用空格分隔】
     */
    private static String[] ipSort(boolean isAsc, String... ipStrs) {
        String[] ipArr = null;
        if (ipStrs.length == 1) {
            String ipStrTemp = ipStrs[0];
            ipStrTemp = replaceIpField(ipStrTemp);
            // 以空格进行分隔
            ipArr = ipStrTemp.split(" +");

        } else {
            ipArr = ipStrs;
            for (int i = 0; i < ipArr.length; i++) {
                ipArr[i] = replaceIpField(ipArr[i].replaceAll(" +", ""));
            }
        }

        if (isAsc) {
            Arrays.sort(ipArr);
        } else {
            ipArr = convertSortArr(ipArr);
        }

        // 显示
        for (String ip : ipArr) {
            // 显示时去电多余的0，只保留有效数值
            System.out.println(ip.replaceAll("0*(\\d+)", "$1"));
        }

        return ipArr;

    }

    /**
     * @param ipStr
     * @return
     */
    private static String replaceIpField(String ipStr) {
        // 对所有ip段的数值补0如127.1.12.123 - 00127.001.0012.00123
        ipStr = ipStr.replaceAll("(\\d+)", "00$1");
        // 对所有数值取3位，舍弃前面的多余的0
        ipStr = ipStr.replaceAll("0*(\\d{3})", "$1");

        return ipStr;
    }

    /**
     * @param ipArr
     * @return
     */
    private static String[] convertSortArr(String[] ipArr) {
        String[] convertIpArr = new String[ipArr.length];
        Arrays.sort(ipArr);
        int j = 0;
        for (int i = ipArr.length - 1; i >= 0; i--) {
            convertIpArr[j] = ipArr[i];
            j++;
        }
        return convertIpArr;
    }

    /**
     * 将字符串 “我我。。。，，，我要、、、我要，，，我要学。。我要学。。。、、、我要学编程！”变为“我要学编程”
     */
    private static void regexTest1() {
        String str = "我我。。。，，，我要、、、要要，，，要学。。学学学编。。。、、、编编编编....编。。。。程..程，，程、、程。。程。。程！";
        String nstr = str.replaceAll("[\\.。，、]", "").replaceAll("(.)\\1+", "$1");
        System.out.println("原字符串：" + str + "\n替换后的字符串：" + nstr);
    }

    private static void regex(String regexStr, String rgx) {
        StringBuilder patternFlsgs = new StringBuilder("Pattern Flags: \n");
        patternFlsgs.append("Pattern.CANON_EQ - ").append(Pattern.CANON_EQ).append("\n")
                .append("Pattern.CASE_INSENSITIVE - ").append(Pattern.CASE_INSENSITIVE).append("\n")
                .append("Pattern.COMMENTS - ").append(Pattern.COMMENTS).append("\n").append("Pattern.DOTALL - ")
                .append(Pattern.DOTALL).append("\n").append("Pattern.LITERAL - ").append(Pattern.LITERAL).append("\n")
                .append("Pattern.MULTILINE - ").append(Pattern.MULTILINE).append("\n")
                .append("Pattern.UNICODE_CASE - ").append(Pattern.UNICODE_CASE).append("\n")
                .append("Pattern.UNICODE_CHARACTER_CLASS - ").append(Pattern.UNICODE_CHARACTER_CLASS).append("\n")
                .append("Pattern.UNIX_LINES - ").append(Pattern.UNIX_LINES).append("\n");

        System.out.println(patternFlsgs.toString());

        Pattern pattern = Pattern.compile(rgx);

        Matcher matcher = pattern.matcher(regexStr);
        boolean matchers = matcher.matches();

        System.out.println("matchers: " + matchers + "\n");

        boolean find = matcher.find();
        System.out.println("find: " + find + "\n");

        int findStart = 0;
        boolean findByStart = matcher.find(findStart);
        System.out.println("findByStart " + findStart + " result: " + findByStart + "\n");

        matcher.reset();
        System.out.println("字符串 " + regexStr + "\n按规则 " + rgx + "\n正则后的结果如下：\n");
        System.out.println("result\tmain\tend");
        while (matcher.find()) {
            System.out.println(matcher.group() + "\t" + matcher.start() + "\t" + matcher.end());
        }
    }

    /**
     * 字符串切割，用正则表达式来切割字符串
     *
     * @param str
     * @param regex
     */
    private static void splitTest(String str, String regex) {
        System.out.println("原字符：" + str);

        String[] arr = str.split(regex);

        System.out.println("按表达式 " + regex + " 分割后的值如下: ");

        for (String s : arr) {
            System.out.println(s);
        }

        System.out.println();
    }

    private static void replaceTest(String str, String oldChar, String newChar) {
        System.out.println("替换前字符串: " + str);

        str = str.replace(oldChar, newChar);
        System.out.println("替换后字符串: " + str);

        System.out.println();
    }

    private static void replaceAllTest(String repStr, String repReg, String replacement) {
        System.out.println("替换前字符串: " + repStr);

        repStr = repStr.replaceAll(repReg, replacement);

        System.out.println("按规则 " + repReg + "替换为 " + replacement + " 后字符串为: " + repStr);
        System.out.println();
    }
}
