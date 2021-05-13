package com.cpt.data.common.enums;

/**
 * redis key enums
 *
 * @author hai
 * @date 2020/11/24 12:29
 */
public enum RedisKeyEnum {

    /**
     * 子订单结算分页key: settlement:order:record:${matchId}
     */
    SETTLEMENT_ORDER_RECORD_PAGE_NO_KEY("settlement:order:record:%s"),

    /**
     * 子订单结算重试key: settlement:order:record:retry:${matchId}
     */
    SETTLEMENT_ORDER_RECORD_RETRY_KEY("settlement:order:record:retry:%s"),

    /**
     * 子订单结算通知重试key: settlement:order:record:retry:notice:${matchId}
     */
    SETTLEMENT_ORDER_RECORD_RETRY_NOTICE_KEY("settlement:order:record:retry:notice:%s"),

    /**
     * 子订单结算记录key: ${tenantCode}:settlement:order:record:${memberId}:${orderRecordId}
     */
    SETTLEMENT_ORDER_RECORD_KEY("%s:settlement:order:record:%s:%s"),

    /**
     * 子订单结算完成记录key: ${tenantCode}:settlement:order:record:done:${memberId}:${orderRecordId}
     */
    SETTLEMENT_ORDER_RECORD_DONE_KEY("%s:settlement:order:record:done:%s:%s"),

    /**
     * 通知指定赛事所有子订单结算load key: settlement:order:record:match:load:${matchId}
     */
    SETTLEMENT_ORDER_RECORD_MATCH_LOAD_KEY("settlement:order:record:match:load:%s"),

    /**
     * 通知所有子订单结算load key: settlement:order:record:all:load
     */
    SETTLEMENT_ORDER_RECORD_ALL_LOAD_KEY("settlement:order:record:all:load"),

    /**
     * 主订单结算分页key: ${tenantCode}:settlement:order:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_PAGE_NO_KEY("%s:settlement:order:%s:%s"),

    /**
     * 主订单结算重试key: ${tenantCode}:settlement:order:retry:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_RETRY_KEY("%s:settlement:order:retry:%s:%s"),

    /**
     * 主订单结算失败重试key: ${tenantCode}:settlement:order:fail:retry:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_FAIL_RETRY_KEY("%s:settlement:order:fail:retry:%s:%s"),

    /**
     * 主订单结算打码量回调处理重试key: ${tenantCode}:settlement:order:retry:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_AMOUNT_RETRY_KEY("%s:settlement:order:amount:retry:%s:%s"),

    /**
     * 主订单结算打码量回调处理mq重试key: ${tenantCode}:settlement:order:retry:mq:${requestSeq}
     */
    SETTLEMENT_ORDER_AMOUNT_RETRY_MQ_KEY("%s:settlement:order:amount:retry:mq:%s"),

    /**
     * 主订单结算记录key: ${tenantCode}:settlement:order:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_KEY("%s:settlement:order:%s:%s"),

    /**
     * 主订单已结算-待加款: ${tenantCode}:settlement:order:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_AMOUNT_KEY("%s:settlement:order:amount:%s:%s"),

    /**
     * 主订单结算完毕: ${tenantCode}:settlement:order:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_DONE_KEY("%s:settlement:order:done:%s:%s"),

    /**
     * 主订单推送成功: ${tenantCode}:settlement:order:${memberId}:${orderId}
     */
    SETTLEMENT_ORDER_PUSH_KEY("%s:settlement:order:push:%s:%s"),

    /**
     * 通知所有主订单结算load key: settlement:order:all:load
     */
    SETTLEMENT_ORDER_ALL_LOAD_KEY("settlement:order:all:load"),

    /**
     * 赛果缓存 HASH KEY
     */
    MATCH_RESULT_CACHE_HASH_KEY("match:result:cache:hash:key");

    private String key;


    RedisKeyEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String format(Object... args) {
        return String.format(this.key, args);
    }

}
