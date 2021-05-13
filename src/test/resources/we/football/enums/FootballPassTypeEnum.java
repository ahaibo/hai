package com.cpt.data.common.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FootballPassTypeEnum {

    /**
     * 单关
     */
    YC1(101, "单关", "one"),

    /**
     * 2 串关
     */
    EC1(201, "2串1", "two"),

    /**
     * 3 串关
     */
    SC1(301, "3串1", "three"),
    SC3(302, "3串3", "three"),
    SC4(303, "3串4", "three"),

    /**
     * 4 串关
     */
    SIC1(401, "4串1", "four"),
    SIC4(402, "4串4", "four"),
    SIC5(403, "4串5", "four"),
    SIC6(404, "4串6", "four"),
    SIC11(405, "4串11", "four"),

    /**
     * 5 串关
     */
    WC1(501, "5串1", "five"),
    WC5(502, "5串5", "five"),
    WC6(503, "5串6", "five"),
    WC10(504, "5串10", "five"),
    WC16(505, "5串16", "five"),
    WC20(506, "5串20", "five"),
    WC26(507, "5串26", "five"),

    /**
     * 6 串关
     */
    LC1(601, "6串1", "six"),
    LC6(602, "6串6", "six"),
    LC7(603, "6串7", "six"),
    LC15(604, "6串15", "six"),
    LC20(605, "6串20", "six"),
    LC22(606, "6串22", "six"),
    LC35(607, "6串35", "six"),
    LC42(608, "6串42", "six"),
    LC50(609, "6串50", "six"),
    LC57(610, "6串57", "six"),

    /**
     * 7 串关
     */
    QC1(701, "7串1", "seven"),
    QC7(702, "7串7", "seven"),
    QC8(703, "7串8", "seven"),
    QC21(704, "7串21", "seven"),
    QC35(705, "7串35", "seven"),
    QC120(706, "7串120", "seven"),

    /**
     * 8 串关
     */
    BC1(801, "8串1", "eight"),
    BC8(802, "8串8", "eight"),
    BC9(803, "8串9", "eight"),
    BC28(804, "8串28", "eight"),
    BC56(805, "8串56", "eight"),
    BC70(806, "8串70", "eight"),
    BC247(807, "8串247", "eight");

    /**
     * 过关编号TAG
     */
    private Integer passTag;

    /**
     * 玩法名称
     */
    private String name;

    /**
     * 串关分类标识
     */
    private String cateTag;

    /**
     * 获取足球过关类型提供给c端使用
     */
    public static Map<Integer, String> PASS_TYPES = new HashMap<>();

    /**
     * 获取足球过关类型列表提供购彩验证
     */
    public static List<String> PASS_TAGS = new ArrayList<>();

    /**
     * N 串 1 tag及串关值
     */
    public static Map<String, Integer> BUNCH1_VALUES = new HashMap<>();

    static {
        for (FootballPassTypeEnum footballPassTypeEnum : FootballPassTypeEnum.values()) {
            PASS_TYPES.put(footballPassTypeEnum.getPassTag(), footballPassTypeEnum.getName());
            PASS_TAGS.add(String.valueOf(footballPassTypeEnum.getPassTag()));
        }

        initBunchValues();
    }

    private static void initBunchValues() {
        BUNCH1_VALUES.put(YC1.passTag.toString(), 1);
        BUNCH1_VALUES.put(EC1.passTag.toString(), 2);
        BUNCH1_VALUES.put(SC1.passTag.toString(), 3);
        BUNCH1_VALUES.put(SIC1.passTag.toString(), 4);
        BUNCH1_VALUES.put(WC1.passTag.toString(), 5);
        BUNCH1_VALUES.put(LC1.passTag.toString(), 6);
        BUNCH1_VALUES.put(QC1.passTag.toString(), 7);
        BUNCH1_VALUES.put(BC1.passTag.toString(), 8);
    }

    FootballPassTypeEnum(Integer passTag, String name, String cateTag) {
        this.passTag = passTag;
        this.name = name;
        this.cateTag = cateTag;
    }

    /**
     * 是否是单关
     *
     * @param type
     * @return
     */
    public static boolean isSingle(String type) {
        return YC1.equals(type);
    }

    public static FootballPassTypeEnum valueOfPassTag(String tagType) {
        if (null == tagType || "".equals(tagType.trim())) {
            return null;
        }
        for (FootballPassTypeEnum footballPassTypeEnum : FootballPassTypeEnum.values()) {
            Integer passTag = Integer.parseInt(tagType);
            if (footballPassTypeEnum.getPassTag().equals(passTag)) {
                return footballPassTypeEnum;
            }
        }
        return null;
    }

    /**
     * 根据过关Tag获取串关方式
     *
     * @return name
     */
    public static String getNameByTag(Integer passTag) {
        for (FootballPassTypeEnum footballPlayOddsEnum : FootballPassTypeEnum.values()) {
            if (footballPlayOddsEnum.getPassTag().equals(passTag)) {
                return footballPlayOddsEnum.getName();
            }
        }
        return null;
    }

    public static String buildPassType(List<FootballPassTypeEnum> list) {
        if (null == list || list.isEmpty()) {
            return "";
        }
        StringBuffer passType = new StringBuffer();
        for (FootballPassTypeEnum typeEnum : list) {
            passType.append(typeEnum.getPassTag()).append(",");
        }
        return passType.substring(0, passType.length() - 1);
    }

    public Integer getPassTag() {
        return passTag;
    }

    public String getName() {
        return name;
    }

    public String getCateTag() {
        return cateTag;
    }

}
