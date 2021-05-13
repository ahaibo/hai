package com.cpt.data.common.enums;

/**
 * 赛果方法类别枚举
 *
 * @author hai
 * @date 2021/4/2 11:56
 */
public enum SgMethodEnum {
    /**
     * 官方取消或者推迟比赛。
     * 推送的比赛状态(0:异常 9:推迟 10:中断 11:腰斩 12:取消 13:待定)
     * [
     * {
     * "matchId": 3526271,
     * "status": 12,
     * "method": 1
     * }
     * ]
     */
    CANCEL_OFFICIAL(1, "官方取消或者推迟比赛"),
    /**
     * 推送修改过的比赛时间, 不包含结束的比赛状态
     * [
     * {
     * "matchId": 3526271,
     * "matchTime": 1617129900000,
     * "status": 4,  // 不含比赛结束的状态
     * "method": 2
     * }
     * ]
     */
    MODIFY_OFFICIAL(2, "官方修改比赛时间或者状态"),
    /**
     * [
     * {
     * "matchId": 3526271,
     * "matchTime": 1617129900,
     * "status": 8,
     * "issueNum": "2001",
     * "homeScore": 1,
     * "awayScore": 0,
     * "halfHomeScore": 0,
     * "halfAwayScore": 0,
     * "spf": "3,2.67",
     * "rq": "+1,3,1.40",
     * "bf": "1:0,6.75",
     * "bqc": "1,3,5.80",
     * "jq": "1,4.00",
     * "method": 3
     * }
     * ]
     */
    SG(3, "比赛结果信息推送"),
    /**
     * [
     * {
     * "matchId": 3526271,
     * "method": 4,
     * "tenantCode": "TYCC",
     * "status": 12,
     * "isDeleted": 1 //预留
     * }
     * ]
     */
    CANCEL_MANUAL(4, "管理后台将比赛置为无效，针对租户");

    /**
     * 消息类别
     */
    private Integer method;

    /**
     * 描述
     */
    private String desc;


    SgMethodEnum(Integer method, String desc) {
        this.method = method;
        this.desc = desc;
    }

    /**
     * 是否为正常赛果
     *
     * @param status
     * @return
     */
    public static boolean isSg(Integer status) {
        return SG.method.equals(status);
    }

    /**
     * 是否为官方取消
     *
     * @param status
     * @return
     */
    public static boolean isCancelByOfficial(Integer status) {
        return CANCEL_OFFICIAL.method.equals(status);
    }

    /**
     * 是否为手动取消
     *
     * @param status
     * @return
     */
    public static boolean isCancelByManual(Integer status) {
        return CANCEL_MANUAL.method.equals(status);
    }

    /**
     * 是否为取消消息
     *
     * @param status
     * @return
     */
    public static boolean isCancelMsg(Integer status) {
        return isCancelByOfficial(status) || isCancelByManual(status);
    }

    /**
     * 是否为修改消息
     *
     * @param status
     * @return
     */
    public static boolean isModify(Integer status) {
        return MODIFY_OFFICIAL.method.equals(status);
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
