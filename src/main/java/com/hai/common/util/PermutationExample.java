package com.hai.common.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 排列组合测试，暂留
 *
 * @author hai
 * @date 2020/11/18 12:43
 */
public class PermutationExample {
    static String[] color = {"红色", "白色", "蓝色", "金色"};
    //static String[] size = {"4.7寸", "5.1寸", "6.0寸", "7.0寸", "8.0寸", "9.0寸"};
    static String[] size = {"4.7寸", "5.1寸"};
    static String[] version = {"联通", "电信", "移动", "全网通"};
    //static String[] arr5 = {"联通2", "电信2", "移动2", "全网通2"};

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(color);
        list.add(size);
        list.add(version);
        //list.add(arr5);

        long start = System.currentTimeMillis();
        List newList = permutation(list);
        long end = System.currentTimeMillis();
        System.out.println("size: " + newList.size() + "; times: " + (end - start) + " .ms");
        System.out.println(JSONObject.toJSONString(list));
        System.out.println(JSONObject.toJSONString(newList));
    }

    /**
     * 排列组合
     *
     * @param list
     * @return
     */
    public static List permutation(List list) {
        //判断数组size是否小于2，如果小于说明已经递归完成
        if (list.size() < 2) {
            return (List) list.get(0);
        }
        //拿到第一个数组
        int len0;
        if (list.get(0) instanceof String[]) {
            String[] arr0 = (String[]) list.get(0);
            len0 = arr0.length;
        } else {
            len0 = ((List<String>) list.get(0)).size();
        }

        //拿到第二个数组
        String[] arr1 = (String[]) list.get(1);
        int len1 = arr1.length;
        //当前两个数组一共能够组成多少个组合
        int lenBoth = len0 * len1;
        //临时存放排列数据的集合
        List<List<String>> tempList = new ArrayList<>(lenBoth);

        //第一层for就是循环 list 第一个元素的
        for (int i = 0; i < len0; i++) {
            //第二层for就是循环 list 第二个元素的
            for (int j = 0; j < len1; j++) {
                //判断第一个元素如果是数组说明循环才刚开始
                if (list.get(0) instanceof String[]) {
                    String[] arr0 = (String[]) list.get(0);
                    List<String> arr = new ArrayList<>();
                    arr.add(arr0[i]);
                    arr.add(arr1[j]);
                    //把排列数据加到临时的集合中
                    tempList.add(arr);
                } else {
                    //到这里说明循环了最少一轮，把上一轮的结果拿出来继续跟 list 的下一个元素排列
                    List<List<String>> temp = (List<List<String>>) list.get(0);
                    List<String> currList = new ArrayList<>();
                    for (int k = 0; k < temp.get(i).size(); k++) {
                        currList.add(temp.get(i).get(k));
                    }
                    currList.add(arr1[j]);
                    tempList.add(currList);
                }
            }
        }

        //根据上面排列的结果重新生成一个集合
        List newArrayList = new ArrayList<>();
        //还没排列的数组装进来，i=2，因为前面两个数组已经完事了
        for (int i = 2; i < list.size(); i++) {
            newArrayList.add(list.get(i));
        }
        //已排列的数据加到新集合的第一位
        newArrayList.add(0, tempList);

        //递归组合
        return permutation(newArrayList);
    }
}
