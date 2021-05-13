package com.cpt.data.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 足球固定玩法枚举类
 *
 * @author lucien
 * @create 2020/11/11 22:20
 */
public enum FootballPlayEnum {

    /**
     * 胜平负
     */
    SPFS(1101, "胜", 0, 1),
    SPFP(1102, "平", 1, 1),
    SPFF(1103, "负", 2, 1),

    /**
     * 让球胜平负
     */
    RQSPFS(1201, "让胜", 0, 2),
    RQSPFP(1202, "让平", 1, 2),
    RQSPFF(1203, "让负", 2, 2),

    /**
     * 胜比分
     */
    SBF1(2101, "1:0", 0, 3),
    SBF2(2102, "2:0", 1, 3),
    SBF3(2103, "2:1", 2, 3),
    SBF4(2104, "3:0", 3, 3),
    SBF5(2105, "3:1", 4, 3),
    SBF6(2106, "3:2", 5, 3),
    SBF7(2107, "4:0", 6, 3),
    SBF8(2108, "4:1", 7, 3),
    SBF9(2109, "4:2", 8, 3),
    SBF10(2110, "5:0", 9, 3),
    SBF11(2111, "5:1", 10, 3),
    SBF12(2112, "5:2", 11, 3),
    SBF13(2113, "胜其他", 12, 3),

    /**
     * 平比分
     */
    PBF1(2201, "0:0", 13, 3),
    PBF2(2202, "1:1", 14, 3),
    PBF3(2203, "2:2", 15, 3),
    PBF4(2204, "3:3", 16, 3),
    PBF5(2205, "平其他", 17, 3),

    /**
     * 负比分
     */
    FBF1(2301, "0:1", 18, 3),
    FBF2(2302, "0:2", 19, 3),
    FBF3(2303, "1:2", 20, 3),
    FBF4(2304, "0:3", 21, 3),
    FBF5(2305, "1:3", 22, 3),
    FBF6(2306, "2:3", 23, 3),
    FBF7(2307, "0:4", 24, 3),
    FBF8(2308, "1:4", 25, 3),
    FBF9(2309, "2:4", 26, 3),
    FBF10(2310, "0:5", 27, 3),
    FBF11(2311, "1:5", 28, 3),
    FBF12(2312, "2:5", 29, 3),
    FBF13(2313, "负其他", 30, 3),

    /**
     * 总进球
     */
    ZJQ1(3001, "0球", 0, 4),
    ZJQ2(3002, "1球", 1, 4),
    ZJQ3(3003, "2球", 2, 4),
    ZJQ4(3004, "3球", 3, 4),
    ZJQ5(3005, "4球", 4, 4),
    ZJQ6(3006, "5球", 5, 4),
    ZJQ7(3007, "6球", 6, 4),
    ZJQ8(3008, "7+球", 7, 4),

    /**
     * 半全场
     */
    BQCSS(4001, "胜胜", 0, 5),
    BQCSP(4002, "胜平", 1, 5),
    BQCSF(4003, "胜负", 2, 5),
    BQCPS(4004, "平胜", 3, 5),
    BQCPP(4005, "平平", 4, 5),
    BQCPF(4006, "平负", 5, 5),
    BQCFS(4007, "负胜", 6, 5),
    BQCFP(4008, "负平", 7, 5),
    BQCFF(4009, "负负", 8, 5);

    /**
     * 玩法编号id
     */
    private Integer playTag;

    /**
     * 玩法名称
     */
    private String playTagName;

    /**
     * 每类玩法对应的赔率下标
     */
    private Integer oddsIndex;

    /**
     * 玩法分类（1：胜平负；2：让球；3：比分；4：进球；5：半全场）
     */
    private Integer cateType;

    /**
     * 获取足球玩法列表提供给c端使用
     */
    public static Map<Integer, String> PLAYS = new HashMap<>();
    /**
     * 玩法，过关类型枚举
     */
    public static Map<String, Object> PLAY_PASS_MAP = new HashMap<>();
    /**
     * 所有胜比分
     */
    public static Set<String> ALL_SBF = new HashSet<>();
    /**
     * 所有平比分
     */
    public static Set<String> ALL_PBF = new HashSet<>();
    /**
     * 所有负比分
     */
    public static Set<String> ALL_FBF = new HashSet<>();

    /**
     * 获取玩法编号集合用于验证玩法参数合法性
     */
    public static List<String> PLAY_TAGS = new ArrayList<>();

    static {
        for (FootballPlayEnum footballPlayOddsEnum : FootballPlayEnum.values()) {
            PLAYS.put(footballPlayOddsEnum.getPlayTag(), footballPlayOddsEnum.getPlayTagName());
            PLAY_TAGS.add(String.valueOf(footballPlayOddsEnum.getPlayTag()));
        }

        //plays:玩法列表; passType:过关列表
        PLAY_PASS_MAP.put("plays", FootballPlayEnum.PLAYS);
        PLAY_PASS_MAP.put("passType", FootballPassTypeEnum.PASS_TYPES);

        initAllSBF();
        initAllPBF();
        initAllFBF();
    }

    private static void initAllSBF() {
        ALL_SBF.add(SBF1.getPlayTagName());
        ALL_SBF.add(SBF2.getPlayTagName());
        ALL_SBF.add(SBF3.getPlayTagName());
        ALL_SBF.add(SBF4.getPlayTagName());
        ALL_SBF.add(SBF5.getPlayTagName());
        ALL_SBF.add(SBF6.getPlayTagName());
        ALL_SBF.add(SBF7.getPlayTagName());
        ALL_SBF.add(SBF8.getPlayTagName());
        ALL_SBF.add(SBF9.getPlayTagName());
        ALL_SBF.add(SBF10.getPlayTagName());
        ALL_SBF.add(SBF11.getPlayTagName());
        ALL_SBF.add(SBF12.getPlayTagName());
    }

    private static void initAllPBF() {
        ALL_PBF.add(PBF1.getPlayTagName());
        ALL_PBF.add(PBF2.getPlayTagName());
        ALL_PBF.add(PBF3.getPlayTagName());
        ALL_PBF.add(PBF4.getPlayTagName());
    }

    private static void initAllFBF() {
        ALL_FBF.add(FBF1.getPlayTagName());
        ALL_FBF.add(FBF2.getPlayTagName());
        ALL_FBF.add(FBF3.getPlayTagName());
        ALL_FBF.add(FBF4.getPlayTagName());
        ALL_FBF.add(FBF5.getPlayTagName());
        ALL_FBF.add(FBF6.getPlayTagName());
        ALL_FBF.add(FBF7.getPlayTagName());
        ALL_FBF.add(FBF8.getPlayTagName());
        ALL_FBF.add(FBF9.getPlayTagName());
        ALL_FBF.add(FBF10.getPlayTagName());
        ALL_FBF.add(FBF11.getPlayTagName());
        ALL_FBF.add(FBF12.getPlayTagName());
    }

    FootballPlayEnum(Integer playTag, String playTagName, Integer oddsIndex, Integer cateType) {
        this.playTag = playTag;
        this.playTagName = playTagName;
        this.oddsIndex = oddsIndex;
        this.cateType = cateType;
    }

    public static boolean isValidPlayTag(String playTag) {
        return PLAY_TAGS.contains(playTag);
    }

    /**
     * 获取对应玩法赔率
     *
     * @param passTag
     * @param spf
     * @param rq
     * @param bf
     * @param jq
     * @param bcq
     * @return
     */
    public static final String getFootballBetOdds(Integer passTag, String spf, String rq, String bf, String jq, String bcq) {
        String[] array = new String[]{null, spf, rq, bf, jq, bcq};
        for (FootballPlayEnum item : FootballPlayEnum.values()) {
            if (item.getPlayTag().equals(passTag)) {
                String odds = array[item.getCateType()];
                if (null == odds || "".equals(odds)) {
                    return null;
                }
                return odds.split(",")[item.getOddsIndex()];
            }
        }
        return null;
    }

    /**
     * 根据playTagId获取玩法名称
     *
     * @param playTag
     * @return
     */
    public static String getByPlayTagName(Integer playTag) {
        for (FootballPlayEnum footballPlayOddsEnum : FootballPlayEnum.values()) {
            if (footballPlayOddsEnum.getPlayTag().equals(playTag)) {
                return footballPlayOddsEnum.getPlayTagName();
            }
        }
        return null;
    }

    public static FootballPlayEnum valueOfPlayTag(Integer playTag) {
        return valueOfPlayTag(String.valueOf(playTag));
    }

    public static FootballPlayEnum valueOfPlayTag(String playTag) {
        if (null == playTag || "".equals(playTag.trim())) {
            return null;
        }
        FootballPlayEnum[] values = FootballPlayEnum.values();
        for (FootballPlayEnum footballPlayOddsEnum : values) {
            if (footballPlayOddsEnum.getPlayTag().toString().equals(playTag)) {
                return footballPlayOddsEnum;
            }
        }
        return null;
    }

    public Integer getPlayTag() {
        return playTag;
    }

    public String getPlayTagName() {
        return playTagName;
    }

    public Integer getOddsIndex() {
        return oddsIndex;
    }

    public Integer getCateType() {
        return cateType;
    }

}
