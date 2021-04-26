package com.hai.we;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 * //size:11, data: [系列,   系列Tag, 玩法, 玩法Tag, 玩法分类, 玩法分类Tag, 组,  组Tag, 属性, 属性Tag, 编号]
 * //size:11, data: [PC系列, 14,     混合, 10,      混合,    1,          混合, 10,   大,   10,      141011010]
 *
 * @author hai
 * @date 2020/12/8 10:52
 */
@Data
public class LotteryBO {

    public static Map<String, Map<String, Object>> SERIES = new HashMap<>();

    static {
        //PCDD

        SERIES.put("14", null);

        //FC
        Map<String, Object> props = new HashMap<>();
        SERIES.put("16", props);
    }

    private String seriesName;
    private String seriesTag;
    private String playName;
    private String playTag;
    private String playClass;
    private String playClassTag;
    private String group;
    private String groupTag;
    private String attr;
    private String attrTag;
    private String number;
    private String date = "2020-12-16 15:00:00";
    private int lotteryId;
    private BigDecimal odds1 = new BigDecimal("9.85");
    private BigDecimal odds2 = BigDecimal.ZERO;
    private BigDecimal minBetAmount = new BigDecimal(1);
    private BigDecimal maxBetAmount = new BigDecimal("99999999.000");
    private int show = 1;
    private int status = 1;
    private int gendan = 0;
    private int sort = 1;
    private String icon = "http://cptuatzx.oss-cn-hongkong.aliyuncs.com/image/head/2020-08-04/dd1ca5f5-350e-4e6a-9204-83ab8a3b5320.png";

    //117:pcdd; 118:fc3d; 119:ssq
    public int getLotteryId() {
        if ("14".equals(seriesTag)) {
            return 117;
        }
        if ("16".equals(seriesTag)) {
            return "29".equals(playTag) ? 119 : 118;
        }
        if ("15".equals(seriesTag)) {
            return "14".equals(playTag) ? 121 : 120;
        }
        throw new RuntimeException("");
    }

    public String obtainPlayTag() {
        return seriesTag.concat(playTag);
    }

    public String obtainPlayClassTag() {
        return seriesTag.concat(playTag).concat(playClassTag);
    }

    public String obtainGroupTag() {
        return seriesTag.concat(playTag).concat(playClassTag).concat(groupTag);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public String genSeriesSql(int id, int size) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `series` VALUES (");
        sql
//                .append(id + size).append(", '")
                .append("id, '")
                .append(seriesTag).append("', '").append(seriesName).append("', ");
        sql.append("'").append(icon).append("', '").append(date).append("', '").append(date).append("');");
        return sql.toString();
    }

    public String genLotterySql(int size) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `lottery` VALUES (");
        sql.append("id").append(", seriesId, '").append(seriesTag).append("', '").append(playName).append("', ");
        sql.append(show).append(", ").append(status).append(", ").append(gendan).append(", ").append(sort).append(", ");
        sql.append("'").append(icon).append("', '").append(icon).append("', ");
        sql.append(getIntterval()).append(", ").append(getIssueFormat()).append(", ");
        sql.append("'").append(date).append("', '").append(date).append("', ");
        //kj code
        sql.append("'").append("").append("', '").append("").append("', '").append("").append("', ");
        sql.append(getLotteryTag()).append(", '").append("").append("', '").append("").append("', ");

        sql.append("'").append(obtainPlayTag()).append("', '").append(obtainPlayClassTag()).append("', '").append(obtainGroupTag()).append("', ");
        sql.append("'").append(group).append("', '").append(number).append("', '").append(attr).append("', ");
        sql.append(odds1).append(", ").append(odds2).append(", ");
        sql.append(minBetAmount).append(", ").append(maxBetAmount).append(", ");
        return sql.toString();
    }

    private int getLotteryTag() {
        return 0;//TODO
    }

    private int getIssueFormat() {
        return 0;//TODO
    }

    private int getIntterval() {
        return 0;//TODO
    }

    public String genPlaySql(int size) {
        StringBuilder sql = new StringBuilder();
//        sql.append("INSERT INTO `lottery_play` VALUES (");
//        sql.append(id).append(", ").append(getLotteryId()).append(", ").append(attrId).append(", ");
//        sql.append("'").append(obtainPlayTag()).append("', '").append(obtainPlayClassTag()).append("', '").append(obtainGroupTag()).append("', ");
//        sql.append("'").append(group).append("', '").append(number).append("', '").append(attr).append("', ");
//        sql.append(odds1).append(", ").append(odds2).append(", ");
//        sql.append(minBetAmount).append(", ").append(maxBetAmount).append(", ");
//        sql.append(status).append(", ").append(gendan).append(", ").append(sort).append(", ");
//        sql.append("'").append(date).append("', '").append(date).append("');");
        return sql.toString();
    }

    public String genPlayClassSql(int size) {
        StringBuilder sql = new StringBuilder();
//        sql.append("INSERT INTO `lottery_play_class` VALUES (");
//        sql.append(id).append(", ").append(getLotteryId()).append(", ").append(attrId).append(", ");
//        sql.append("'").append(obtainPlayTag()).append("', '").append(obtainPlayClassTag()).append("', '").append(obtainGroupTag()).append("', ");
//        sql.append("'").append(group).append("', '").append(number).append("', '").append(attr).append("', ");
//        sql.append(odds1).append(", ").append(odds2).append(", ");
//        sql.append(minBetAmount).append(", ").append(maxBetAmount).append(", ");
//        sql.append(status).append(", ").append(gendan).append(", ").append(sort).append(", ");
//        sql.append("'").append(date).append("', '").append(date).append("');");
        return sql.toString();
    }

    public String genPlayClassGroupSql(int size) {
        StringBuilder sql = new StringBuilder();
//        sql.append("INSERT INTO `lottery_play_class_group` VALUES (");
//        sql.append(id).append(", ").append(getLotteryId()).append(", ").append(attrId).append(", ");
//        sql.append("'").append(obtainPlayTag()).append("', '").append(obtainPlayClassTag()).append("', '").append(obtainGroupTag()).append("', ");
//        sql.append("'").append(group).append("', '").append(number).append("', '").append(attr).append("', ");
//        sql.append(odds1).append(", ").append(odds2).append(", ");
//        sql.append(minBetAmount).append(", ").append(maxBetAmount).append(", ");
//        sql.append(status).append(", ").append(gendan).append(", ").append(sort).append(", ");
//        sql.append("'").append(date).append("', '").append(date).append("');");
        return sql.toString();
    }

    public String genPlayClassGroupAttrSql(int id, int attrId) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `lottery_play_class_group_attribute` VALUES (");
        sql.append(id).append(", ").append(getLotteryId()).append(", ").append(attrId).append(", ");
        sql.append("'").append(obtainPlayTag()).append("', '").append(obtainPlayClassTag()).append("', '").append(obtainGroupTag()).append("', ");
        sql.append("'").append(group).append("', '").append(number).append("', '").append(attr).append("', ");
        sql.append(odds1).append(", ").append(odds2).append(", ");
        sql.append(minBetAmount).append(", ").append(maxBetAmount).append(", ");
        sql.append(status).append(", ").append(gendan).append(", ").append(sort).append(", ");
        sql.append("'").append(date).append("', '").append(date).append("');");
        return sql.toString();
    }
}
