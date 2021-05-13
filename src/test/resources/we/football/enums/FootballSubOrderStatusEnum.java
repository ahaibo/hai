package com.cpt.data.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 足球子订单状态枚举
 * 订单状态（2:待开奖 5:未中奖 6:已中奖  10:已撤单 11:处理失败 12:扣款失败 20:异常订单）
 *
 * @author hai
 * @date 2020/11/17 15:54
 */
public enum FootballSubOrderStatusEnum {
    /**
     * 待开奖: 2
     */
    DKJ(2, "待开奖"),
    /**
     * 未中奖: 5
     */
    LOSE(5, "未中奖"),
    /**
     * 已中奖: 6
     */
    WIN(6, "已中奖"),
    /**
     * 已撤单: 10
     */
    YCD(10, "已撤单"),
    /**
     * 处理失败:11 预留
     */
    FAIL(11, "处理失败"),
    /**
     * 扣款失败: 12
     */
    FAIL_KK(12, "扣款失败"),
    /**
     * 异常订单:20 预留
     */
    ERROR(20, "异常订单"),
    /**
     * 已取消比赛订单:21
     */
    CANCEL(21, "已取消比赛订单");

    private Integer status;
    private String desc;


    public static List<Integer> COMPENSATE_STATUS = new ArrayList<>();

    static {
        COMPENSATE_STATUS.add(DKJ.status);
        COMPENSATE_STATUS.add(FAIL.status);
        COMPENSATE_STATUS.add(ERROR.status);
    }

    FootballSubOrderStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 是否为异常状态
     *
     * @param status
     * @return
     */
    public static boolean isErrorStatus(Integer status) {
        return FAIL.getStatus().equals(status) || FAIL_KK.getStatus().equals(status) || ERROR.getStatus().equals(status);
    }

    /**
     * 是否为已取消比赛订单状态
     *
     * @param status
     * @return
     */
    public static boolean isCancelStatus(Integer status) {
        return CANCEL.getStatus().equals(status);
    }

    /**
     * 是否为待开奖状态
     *
     * @param status
     * @return
     */
    public static boolean isDkjStatus(Integer status) {
        return DKJ.getStatus().equals(status);
    }

    /**
     * 是否为中奖状态
     *
     * @param status
     * @return
     */
    public static boolean isWinStatus(Integer status) {
        return WIN.getStatus().equals(status);
    }

    /**
     * 是否为未中奖状态
     *
     * @param status
     * @return
     */
    public static boolean isLoseStatus(Integer status) {
        return LOSE.getStatus().equals(status);
    }

    /**
     * 是否是已开奖状态
     *
     * @param status
     * @return
     */
    public static boolean isSettlementStatus(Integer status) {
        return WIN.getStatus().equals(status) || LOSE.getStatus().equals(status);
    }

    /**
     * 是否为中奖状态，返回status值
     *
     * @param isWin
     * @return
     */
    public static int evalWinStatusValue(boolean isWin) {
        return evalWinStatus(isWin).getStatus().intValue();
    }

    /**
     * 是否为中奖状态，返回 FootballSubOrderStatusEnum
     *
     * @param isWin
     * @return
     */
    public static FootballSubOrderStatusEnum evalWinStatus(boolean isWin) {
        return isWin ? WIN : LOSE;
    }

    /**
     * 根据状态值获取枚举
     *
     * @param status
     * @return
     */
    public static FootballSubOrderStatusEnum valueOfStatus(Integer status) {
        if (null == status) {
            return null;
        }
        FootballSubOrderStatusEnum[] values = FootballSubOrderStatusEnum.values();
        for (FootballSubOrderStatusEnum subOrderStatusEnum : values) {
            if (subOrderStatusEnum.getStatus().equals(status)) {
                return subOrderStatusEnum;
            }
        }
        return null;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
