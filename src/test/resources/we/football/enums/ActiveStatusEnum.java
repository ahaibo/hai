package com.cpt.data.common.enums;

/**
 * 启停/是否等通用枚举
 *
 * @author hai
 * @date 2020/11/17 16:04
 */
public enum ActiveStatusEnum {

    /**
     * 激活类状态：1
     */
    ACTIVE(1, "激活状态"),
    /**
     * 非激活类状态：0
     */
    INACTIVE(0, "非激活状态");

    private Integer value;
    private String desc;

    ActiveStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static boolean isActive(Integer value) {
        return ACTIVE.getValue().equals(value);
    }

    public static boolean isInActive(Integer value) {
        return INACTIVE.getValue().equals(value);
    }

    public static String evalDisableStatusText(Integer status) {
        return evalStatusText(status, "启用", "禁用");
    }

    public static String evalStatusText(Integer status, String activeText, String inactiveText) {
        if (ACTIVE.value.equals(status)) {
            return activeText;
        }
        return inactiveText;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
