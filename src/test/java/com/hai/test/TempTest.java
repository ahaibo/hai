/**
 *
 */
package com.hai.test;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONObject;
import com.hai.common.util.DateUtils;
import com.hai.we.LotteryBO;
import org.apache.groovy.util.Maps;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class TempTest {

    @Test
    public void test() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

        long a = 123L;
        System.out.println((int) a);
    }

    public static void main(String[] args) {
//        testRandomNumber();
//        String filePath = "C:\\Users\\Administrator\\Documents\\xgc.id.txt";
        //String filePath = "E:\\notes\\account\\lht-id.txt";
//        String filePath = "D:\\lhc-009.txt";
        String filePath = "D:\\hk-lhc-009.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder sb = new StringBuilder();
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println("update app_member set vip_id=5 where account='" + line + "';");
//                System.out.println("update app_member set user_type=5 where account='" + line + "';");
//                System.out.print("" + line + ",");
//                System.out.print("APP_MEMBER_" + line + ",");
                String[] arr = line.split(",");
                BigDecimal balance = new BigDecimal(arr[0]);
                sb.append("update app_member set balance=balance-").append(balance).append(" where id=").append(Integer.parseInt(arr[1])).append(";"
                ).append("\n");
//                sb.append("APP_MEMBER_").append(line).append(",");
//                if (++count % 100 == 0) {
//                    System.out.println(sb);
//                    sb = new StringBuilder();
//                }
            }
            System.out.println(sb);
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
//        String filePath = "E:\\notes\\account\\lht-id.txt";
        String filePath = "E:\\notes\\sql\\cpt\\pay-user-id.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            StringBuilder sb = new StringBuilder();
            int count = 0;
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println("update app_member set vip_id=5 where account='" + line + "';");
//                System.out.println("update app_member set user_type=5 where account='" + line + "';");
//                System.out.print("" + line + ",");
//                System.out.print("APP_MEMBER_" + line + ",");
                sb.append("APP_MEMBER_").append(line).append(",");
                if (++count % 200 == 0) {
                    System.out.println(sb);
                    sb = new StringBuilder();
                }
            }
            System.out.println(sb);
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
    public void testBigDecimal() {
        BigDecimal num = new BigDecimal("2");
        System.out.println(num.compareTo(BigDecimal.ZERO));
    }

    @Test
    public void testShardingCalc() {
        String data = "CSZH" + "7566373065738304";
        int hashCode = data.hashCode();
        int db = (hashCode / 100) % 2;
        int table = hashCode % 4;
        System.out.println(db + "\n" + table);
    }

    @Test
    public void clearMemberCache() {
//        String filePath = "D:\\hai\\Notes\\account\\lht-account.txt";
        //String filePath = "E:\\notes\\we\\lottery\\data-pcdd.txt";
//        String filePath = "E:\\notes\\we\\lottery\\data-pl35.txt";
        String filePath = "E:\\notes\\we\\lottery\\data-dlt.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            //INSERT INTO `lottery_play_class_group_attribute` VALUES (30829, 117, 16128, '1410', '14101', '1410110', '大', '141011010', '10', 180
            // .000, 0.000, 1.000, 99999999.000, 1, 0, 1, '2020-12-08 15:00:00', '2020-12-08 15:00:00');

            List<LotteryBO> lotteryBOList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
//                System.out.print("'" + line + "',");
                //line = line.replaceAll("\\t*", ",");
//                System.out.println(line);
                String[] arr = line.split("\t");
                //System.out.println(Arrays.toString(arr));
                List<String> list = new ArrayList();
                for (String str : arr) {
                    if (null == str || "".equals(str.trim())) {
                        continue;
                    }
                    list.add(str);
                }
                if (list.isEmpty()) {
                    continue;
                }
                //System.out.println("list: - " + list);
                //System.out.println();
                //size:11, data: [系列, 系列Tag, 玩法, 玩法Tag, 玩法分类, 玩法分类Tag, 组, 组Tag, 属性, 属性Tag, 编号]
                //size:11, data: [PC系列, 14, 混合, 10, 混合, 1, 混合, 10, 大, 10, 141011010]
                LotteryBO lotteryBO = new LotteryBO();
                lotteryBO.setSeriesName(list.get(0));
                lotteryBO.setSeriesTag(list.get(1));
                lotteryBO.setPlayName(list.get(2));
                lotteryBO.setPlayTag(list.get(3));
                lotteryBO.setPlayClass(list.get(4));
                lotteryBO.setPlayClassTag(list.get(5));
                lotteryBO.setGroup(list.get(6));
                lotteryBO.setGroupTag(list.get(7));
                lotteryBO.setAttr(list.get(8));
                lotteryBO.setAttrTag(list.get(9));
                lotteryBO.setNumber(list.get(10));
                lotteryBOList.add(lotteryBO);
                //System.out.println("lotteryBO - " + lotteryBO + "\n");
            }

            int id = 32419;
            int attrId = 16965;
            Set<String> seriesSql = new HashSet<>();
            Set<String> lotterySql = new HashSet<>();
            Set<String> playSql = new HashSet<>();
            Set<String> playClassSql = new HashSet<>();
            Set<String> playClassGroupSql = new HashSet<>();
            List<String> playClassGroupAttrSql = new ArrayList<>();
            for (int i = 0, size = lotteryBOList.size(); i < size; i++) {
                LotteryBO bo = lotteryBOList.get(i);
                String sql = bo.genPlayClassGroupAttrSql(id, attrId);
                System.out.println(sql);
                //seriesSql.add(bo.genSeriesSql(seriesSql.size()));
                //lotterySql.add(bo.genLotterySql(lotterySql.size()));
                //playSql.add(bo.genPlaySql(playSql.size()));
                //playClassSql.add(bo.genPlayClassSql(playClassSql.size()));
                //playClassGroupSql.add(bo.genPlayClassGroupSql(playClassGroupSql.size()));
                playClassGroupAttrSql.add(sql);
                id++;
                attrId++;
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

//    @Test
//    public void test3() {
//        for (int i = 0; i < 100000; i++) {
//            long time1 = System.currentTimeMillis(), time2 = time1 / 1000;
//            System.out.print(time1);
//            System.out.print(",");
//            System.out.print(time2);
//            System.out.println();
//        }
//    }

    @Test
    public void test4() {
        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(new String[]{"管理员账号" + i, "客户账号" + i, "客户账号" + i});
        }

        System.out.println(JSONObject.toJSONString(list));
    }

    @Test
    public void genDateRange() {
        String start = "20201001";
        String end = "20201101";
        String format = "yyyyMMdd";

        Date startDate = DateUtils.parseDate(start, format);
        Date endDate = DateUtils.parseDate(end, format);
        StringBuilder sb = new StringBuilder();
        while (startDate.before(endDate) || startDate.equals(endDate)) {
            sb.append(DateUtils.formatDate(startDate, format)).append(",");
            startDate = DateUtils.addDays(startDate, 1);
        }
        if (sb.length() > 0) {
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb);
    }

    @Test
    public void excelTest() {
//        String filePath = "E:\\notes\\we\\lottery\\data-pcdd.txt";
        String filePath = "C:\\Users\\Administrator\\Desktop\\pcdd.xls";
        ExcelReader reader = ExcelUtil.getReader(filePath);
        List<List<Object>> lists = reader.read();
        lists.forEach(list -> {
            System.out.println(list);
        });
    }


    @Test
    public void fbPlayCombine() {
        // String filePath = "D:\\datas\\8421465597266368 (3).txt";
        String filePath = "D:\\datas\\8421465597266368-me.txt";
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            //INSERT INTO `lottery_play_class_group_attribute` VALUES (30829, 117, 16128, '1410', '14101', '1410110', '大', '141011010', '10', 180
            // .000, 0.000, 1.000, 99999999.000, 1, 0, 1, '2020-12-08 15:00:00', '2020-12-08 15:00:00');

            Map<String, List<String>> map = new HashMap<>();
            Map<String, List<String>> cancelMap = new HashMap<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("-");
                String key = arr[1];
                String cancel = arr[4];

                if ("取消,取消".equals(cancel)) {
                    List<String> list = cancelMap.get(key);
                    if (null == list) {
                        list = new ArrayList<>();
                    }
                    list.add(line);
                    cancelMap.put(key, list);
                } else {
                    List<String> list = map.get(key);
                    if (null == list) {
                        list = new ArrayList<>();
                    }
                    list.add(line);
                    map.put(key, list);
                }
            }
            System.out.println(JSONObject.toJSONString(map));
            System.out.println("----------------------------------------------------------------------------------------------------------");
            System.out.println(JSONObject.toJSONString(cancelMap));
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
    public void testThreadLocal() {
        ThreadLocal<AtomicInteger> sequnce = ThreadLocal.withInitial(() -> new AtomicInteger(0));
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(sequnce.get().getAndIncrement());
                //ThreadLocal使用万一定要remove，不然容易造成内存泄漏，数据错乱等问题
                sequnce.remove();
            });
        }

        executor.shutdown();
    }

    @Test
    public void integer() {
        String a = "abc";
        String b = "abc";
        System.out.println(a == b);

        Integer i1 = 256;
        Integer i2 = 256;
        System.out.println(i1 == i2);
    }

}
