package com.hai.we;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author hai
 * @date 2021/5/6 12:19
 */
public class GenSql {

    @Test
    public void genUsaMMSql() {
        String filePath = "E:\\notes\\we\\lottery\\data-usa-mm.txt";
        int attrId = 33741;
        int attributeId = 18167;
        int lotteryId = 128;
        genLotterySql(filePath, lotteryId, attrId, attributeId);
    }

    @Test
    public void genUsaPowerSql() {
        String filePath = "E:\\notes\\we\\lottery\\data-usa-power.txt";
        int attrId = 33836;
        int attributeId = 18262;
        int lotteryId = 129;
        genLotterySql(filePath, lotteryId, attrId, attributeId);
    }

    public void genLotterySql(String filePath, int lotteryId, int attrId, int attributeId) {
//        String filePath = "D:\\hai\\Notes\\account\\lht-account.txt";
        //String filePath = "E:\\notes\\we\\lottery\\data-pcdd.txt";
//        String filePath = "E:\\notes\\we\\lottery\\data-pl35.txt";
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


            // int id = 33835;
            // int attrId = 18261;
            // int lotteryId = 129;
            Set<String> seriesSql = new HashSet<>();
            Set<String> lotterySql = new HashSet<>();
            Set<String> playSql = new HashSet<>();
            Set<String> playClassSql = new HashSet<>();
            Set<String> playClassGroupSql = new HashSet<>();
            List<String> playClassGroupAttrSql = new ArrayList<>();
            for (int i = 0, size = lotteryBOList.size(); i < size; i++) {
                LotteryBO bo = lotteryBOList.get(i);
                String sql = bo.genPlayClassGroupAttrSql(attrId, attributeId, lotteryId);
                System.out.println(sql);
                //seriesSql.add(bo.genSeriesSql(seriesSql.size()));
                //lotterySql.add(bo.genLotterySql(lotterySql.size()));
                //playSql.add(bo.genPlaySql(playSql.size()));
                //playClassSql.add(bo.genPlayClassSql(playClassSql.size()));
                //playClassGroupSql.add(bo.genPlayClassGroupSql(playClassGroupSql.size()));
                playClassGroupAttrSql.add(sql);
                attrId++;
                attributeId++;
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

}
