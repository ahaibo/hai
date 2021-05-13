package com.cpt.data.common.enums;

/**
 * 结算加减款状态
 * -1-待处理，0-成功，1-失败
 *
 * @author hai
 * @date 2020/11/26 16:36
 */
public enum SettlementAwardEnum {

    AWARD_WAIT(-1, "待处理"),
    AWARD_SUCCESS(0, "成功"),
    AWARD_FAIL(1, "失败");

    private Integer status;
    private String desc;

    public static boolean isSuccess(Integer status) {
        return AWARD_SUCCESS.status.equals(status);
    }

    public static boolean isFailed(Integer status) {
        return AWARD_FAIL.status.equals(status);
    }

    public static boolean isWait(Integer status) {
        return AWARD_WAIT.status.equals(status);
    }

    SettlementAwardEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
