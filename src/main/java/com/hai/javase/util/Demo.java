package com.hai.javase.util;

import java.util.ArrayList;

import java.util.List;

public class Demo {

    private char ZERO = '0';

    private char ZERO_CN = '零';

    private String[] NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private String[] UNIT = {"", "拾", "佰", "仟"};

    private String[] MATRIX = {"", "万", "亿", "兆", "吉", "太", "拍", "艾", "泽", "尧"};

    public static void main(String[] args) {
        Demo demo = new Demo();

        System.out.println(demo.change(102340001));
    }

    public String change(long num) {

        if (num < 0) {

            return "";

        }

        List<String> data = new ArrayList<String>();

        format(String.valueOf(num), data);

        return show(data);

    }

    public void format(String str, List<String> data) {

        int len = str.length();

        if (len == 1) {

            data.add(NUMBER[str.charAt(0) - ZERO]);

            return;

        }

        if (len > 4) {

            format(str.substring(str.length() - 4), data);

            format(str.substring(0, str.length() - 4), data);

            return;

        }

        formatUnit(str, data);

    }

    private void formatUnit(String str, List<String> data) {

        String result = "";

        while (str.length() > 0 && Long.parseLong(str) > 0) {

            if (str.charAt(0) == ZERO) {

                result += NUMBER[str.charAt(0) - ZERO];

                str = String.valueOf(Long.parseLong(str));

                continue;

            }

            result += NUMBER[str.charAt(0) - ZERO] + UNIT[str.length() - 1];

            str = str.substring(1);

        }

        data.add(result);

    }

    private String show(List<String> data) {

        String result = "";

        for (int i = data.size() - 1; i >= 0; i--) {

            String str = data.get(i);

            if (str.length() == 0) {

                continue;

            }

            result += data.get(i) + MATRIX[i];

        }

        return trimZeroCN(result);

    }

    private String trimZeroCN(String str) {

        if (str.length() <= 1) {

            return str;

        }

        char[] ch = str.toCharArray();

        int start = 0;

        int end = str.length() - 1;

        while (ch[start] == ZERO_CN) {

            start++;

        }

        while (ch[end] == ZERO_CN) {

            end--;

        }

        return str.substring(start, end + 1);

    }

}