package com.cpt.data.common.enums;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 比赛状态枚举
 *
 * @author hai
 * @date 2021/3/5 15:56
 */
public enum MatchStatusEnum {
    //'比赛状态| 0| 比赛异常，说明：暂未判断具体原因的异常比赛，可能但不限于：腰斩、取消等等，建议隐藏处理|
    // 1| 未开赛| 2|上半场| 3|中场| 4|下半场| 5|加时赛| 6|加时赛(弃用)| 7|点球决战| 8|完场| 9|推迟| 10|中断| 11|腰斩| 12|取消| 13|待定
    ERROR(0, false, "比赛异常"),
    NO_START(1, true, "未开赛"),
    SBC(2, true, "上半场"),
    ZC(3, true, "中场"),
    XBC(4, true, "下半场"),
    TIME_EXT(5, true, "加时赛"),
    TIME_EXT_ABANDON(6, true, "加时赛(弃用)"),
    DQ(7, true, "点球决战"),
    DONE(8, true, "完场"),
    DELAY(9, false, "推迟"),
    INTERRUPT(10, false, "中断"),
    YZ(11, false, "腰斩"),
    CANCEL(12, false, "取消"),
    INDETERMINATE(13, false, "待定"),
    ;
    /**
     * 比赛状态
     */
    private Integer status;
    /**
     * 是否是正常状态
     */
    private boolean normal;
    /**
     * 描述
     */
    private String desc;

    static Map<Integer, MatchStatusEnum> ALL_ITEMS = new HashMap<>();
    static Set<Integer> ALL_SETTLEMENT_STATUS = new HashSet<>();
    static Set<Integer> ALL_MATCH_STATUS = new HashSet<>();

    static {
        for (MatchStatusEnum item : MatchStatusEnum.values()) {
            ALL_ITEMS.put(item.getStatus(), item);
        }
        //所有可结算状态
        initAllValidStatus();
        //所有进行中状态
        initAllMatchStatus();
    }

    private static void initAllMatchStatus() {
        ALL_MATCH_STATUS.add(SBC.status);
        ALL_MATCH_STATUS.add(ZC.status);
        ALL_MATCH_STATUS.add(XBC.status);
        ALL_MATCH_STATUS.add(TIME_EXT.status);
        ALL_MATCH_STATUS.add(TIME_EXT_ABANDON.status);
        ALL_MATCH_STATUS.add(DQ.status);
    }

    private static void initAllValidStatus() {
        ALL_SETTLEMENT_STATUS.add(ERROR.status);
        ALL_SETTLEMENT_STATUS.add(DONE.status);
        ALL_SETTLEMENT_STATUS.add(DELAY.status);
        ALL_SETTLEMENT_STATUS.add(INTERRUPT.status);
        ALL_SETTLEMENT_STATUS.add(YZ.status);
        ALL_SETTLEMENT_STATUS.add(CANCEL.status);
        ALL_SETTLEMENT_STATUS.add(INDETERMINATE.status);
    }

    MatchStatusEnum(Integer status, boolean normal, String desc) {
        this.status = status;
        this.normal = normal;
        this.desc = desc;
    }

    /**
     * 比赛是否为可结算状态
     *
     * @param status
     * @return
     */
    public static boolean isSettlementStatus(Integer status) {
        return ALL_SETTLEMENT_STATUS.contains(status);
    }

    /**
     * 比赛是否为未开始状态
     *
     * @param status
     * @return
     */
    public static boolean isNoStartStatus(Integer status) {
        return NO_START.status.equals(status);
    }

    /**
     * 比赛是否为未完结状态
     *
     * @param status
     * @return
     */
    public static boolean isNoDoneStatus(Integer status) {
        return isNoStartStatus(status) || isMatchStatus(status);
    }

    /**
     * 比赛是否为完结状态
     *
     * @param status
     * @return
     */
    public static boolean isDoneStatus(Integer status) {
        return DONE.status.equals(status);
    }

    /**
     * 比赛是否为比赛中状态
     *
     * @param status
     * @return
     */
    public static boolean isMatchStatus(Integer status) {
        return ALL_MATCH_STATUS.contains(status);
    }

    /**
     * 比赛是否为正常状态
     *
     * @param status
     * @return
     */
    public static boolean isNormalStatus(Integer status) {
        MatchStatusEnum item = ALL_ITEMS.get(status);
        if (null == item) {
            //throw new RuntimeException(String.format("比赛状态异常: %s", status));
            return false;
        }
        return item.normal;
    }

    /**
     * 比赛是否为取消状态
     *
     * @param status
     * @return
     */
    public static boolean isCancelStatus(Integer status) {
        return !isNormalStatus(status);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
