package com.cpt.data.common.enums;

/**
 * 过关类型枚举
 * 过关类型（1：胜平负；2：比分；3：进球数；4：半全场；5：混合过关）
 *
 * @author hai
 * @date 2020/11/17 15:58
 */
public enum BunchTypeEnum {
    /**
     * 胜平负: 1
     */
    DKJ(1, "胜平负"),
    /**
     * 比分: 2
     */
    LOSE(2, "比分"),
    /**
     * 进球数: 3
     */
    WIN(3, "进球数"),
    /**
     * 半全场: 4
     */
    YCD(4, "半全场"),
    /**
     * 混合过关: 5 预留
     */
    FAIL(5, "混合过关");

    private Integer status;
    private String desc;

    BunchTypeEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 根据状态值获取枚举
     *
     * @param status
     * @return
     */
    public static BunchTypeEnum valueOfStatus(Integer status) {
        if (null == status) {
            return null;
        }
        BunchTypeEnum[] values = BunchTypeEnum.values();
        for (BunchTypeEnum bunchTypeEnum : values) {
            if (bunchTypeEnum.getStatus().equals(status)) {
                return bunchTypeEnum;
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
