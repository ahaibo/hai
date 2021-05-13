package com.cpt.data.common.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 足球主订单状态枚举
 * 订单状态（1:待扣款 2:待开奖 3:已中奖待加款 5:未中奖 6:已中奖  7:撤单待加款 10:已撤单 11:处理失败 12:扣款失败 20:异常订单）
 *
 * @author hai
 * @date 2020/11/17 15:42
 */
public enum FootballMainOrderStatusEnum {
    /**
     * 待扣款: 1
     */
    DKK(1, "待扣款"),
    /**
     * 待开奖: 2
     */
    DKJ(2, "待开奖"),
    /**
     * 已中奖待加款: 3
     */
    ZJDJK(3, "已中奖待加款"),
    /**
     * 未中奖: 5
     */
    LOSE(5, "未中奖"),
    /**
     * 已中奖: 6
     */
    WIN(6, "已中奖"),
    /**
     * 撤单待加款: 7
     */
    CDDJK(7, "撤单待加款"),
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
    ERROR(20, "异常订单");

    private Integer status;
    private String desc;

    FootballMainOrderStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 是否是待开奖状态
     *
     * @param status
     * @return
     */
    public static boolean isDkjStatus(Integer status) {
        return DKJ.getStatus().equals(status);
    }

    /**
     * 是否是中奖待加款状态
     *
     * @param status
     * @return
     */
    public static boolean isZjdjkStatus(Integer status) {
        return ZJDJK.getStatus().equals(status);
    }

    /**
     * 是否是中奖待状态
     *
     * @param status
     * @return
     */
    public static boolean isWinStatus(Integer status) {
        return WIN.getStatus().equals(status);
    }

    /**
     * 是否是处理失败待状态
     *
     * @param status
     * @return
     */
    public static boolean isFailStatus(Integer status) {
        return FAIL.getStatus().equals(status);
    }

    /**
     * 获取中奖状态
     *
     * @param isWin
     * @return
     */
    public static int evalWinStatus(boolean isWin) {
        return isWin ? WIN.getStatus() : LOSE.getStatus();
    }

    /**
     * 根据状态值获取枚举
     *
     * @param status
     * @return
     */
    public static FootballMainOrderStatusEnum valueOfStatus(Integer status) {
        if (null == status) {
            return null;
        }
        FootballMainOrderStatusEnum[] values = FootballMainOrderStatusEnum.values();
        for (FootballMainOrderStatusEnum mainOrderStatusEnum : values) {
            if (mainOrderStatusEnum.getStatus().equals(status)) {
                return mainOrderStatusEnum;
            }
        }
        return null;
    }

    /**
     * 获取主订单待开奖状态列表
     *
     * @return
     */
    public static List<Integer> getDkjStatusList() {
        return Arrays.asList(DKJ.status, ZJDJK.status, FAIL.status);
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
