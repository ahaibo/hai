package com.hai.common.util.redis;

/**
 * Redis 所有Key 集中类
 */
public class RedisKeys {

    /** 彩种对象key: key后拼接id */
    public static final String LOTTERY_KEY = "LOTTERY_KEY_";

    /** 用户对象key: key后拼接id */
    public static final String APP_MEMBER = "APP_MEMBER_";

//    /** 用户 余额 */
//    public static final String APP_MEMBER_BAL = "APP_MEMBER_BAL_";

    /** 用户名称key: key后拼接id */
    public static final String APP_NICKNAME_NAME = "APP_NICKNAME_NAME_";

    /** 用户名称key: key后拼接id */
    public static final String APP_MEMBER_IDANDNAME = "APP_MEMBER_IDANDNAME_";

    /** 后台用户名称key: key后拼接id */
    public static final String REFERRE_MEMBER_IDANDNAME = "REFERRE_MEMBER_IDANDNAME_";

    /** 所有彩种集合key */
    public static final String LOTTERY_CATEGORY_LIST_KEY = "LOTTERY_CATEGORY_LIST_KEY";
    public static final String LOTTERY_CATEGORY_MAP_KEY = "LOTTERY_CATEGORY_MAP_KEY";

    /** 所有彩票集合key */
    public static final String LOTTERY_LIST_KEY = "LOTTERY_LIST_KEY";
    /** 所有彩种MAP集合key */
    public static final String LOTTERY_MAP_KEY = "LOTTERY_MAP_KEY";

    /** 所有彩票集合key：包含已删除彩种 */
    public static final String LOTTERY_ALL_LIST_KEY = "LOTTERY_ALL_LIST_KEY";
    /** 所有彩种MAP集合key：包含已删除彩种 */
    public static final String LOTTERY_ALL_MAP_KEY = "LOTTERY_ALL_MAP_KEY";

    /** 所有彩种玩法集合key */
    public static final String LOTTERY_PLAY_LIST_KEY = "LOTTERY_PLAY_LIST_KEY";

    /** 所有彩种玩法MAP集合key */
    public static final String LOTTERY_PLAY_MAP_KEY = "LOTTERY_PLAY_MAP_KEY";

    /** 投注限制对象key: key后拼接id */
    public static final String BONUS_KEY = "BONUS_KEY_";

    /** 赔率配置列表 */
    public static final String ODDS_LIST_SETTING_KEY = "ODDS_LIST_SETTING_";

    /** 赔率配置列表 */
    public static final String ODDS_LIST_LONG_Dragon_SETTING_KEY = "ODDS_LIST_LONG_Dragon_SETTING_KEY_";

    /** 六合彩赔率配置列表 */
    public static final String LHC_ODDS_LIST_LONG_DRAGON_SETTING_KEY = "LHC_ODDS_LIST_LONG_Dragon_SETTING_KEY_";

    /** 数据值等级列表 */
    public static final String DATA_VALUE_LEVEL = "DATA_VALUE_LEVEL_";

    /** 心水更新点赞、阅读数列表 */
    public static final String XS_ADMIRE_COMTENT_VALUE = "XS_ADMIRE_COMTENT_VALUE_CACLE";

    /**
     * ==================================赛果缓存============================================*
     */

    /** 重庆时时彩赛果 */
    public static final String CQSSC_RESULT_VALUE = "CQSSC_RESULT_VALUE_1101";

    public static final String CQSSC_NEXT_VALUE = "CQSSC_NEXT_VALUE_1101";

    public static final String CQSSC_ALGORITHM_VALUE = "CQSSC_ALGORITHM_VALUE_1101";// 计算单双，大小，五行数据

    /** 新疆时时彩赛果 */
    public static final String XJSSC_RESULT_VALUE = "XJSSC_RESULT_VALUE_1102";

    public static final String XJSSC_NEXT_VALUE = "XJSSC_NEXT_VALUE_1102";

    public static final String XJSSC_ALGORITHM_VALUE = "XJSSC_ALGORITHM_VALUE_1102";// 计算单双，大小，五行数据

    /** 天津时时彩赛果 */
    public static final String TJSSC_RESULT_VALUE = "TJSSC_RESULT_VALUE_1103";

    public static final String TJSSC_NEXT_VALUE = "TJSSC_NEXT_VALUE_1103";

    public static final String TJSSC_ALGORITHM_VALUE = "TJSSC_ALGORITHM_VALUE_1103";// 计算单双，大小，五行数据

    /** 10分时时彩赛果 */
    public static final String TENSSC_RESULT_VALUE = "TENSSC_RESULT_VALUE_1104";

    public static final String TENSSC_NEXT_VALUE = "TENSSC_NEXT_VALUE_1104";

    public static final String TENSSC_ALGORITHM_VALUE = "TENSSC_ALGORITHM_VALUE_1104";// 计算单双，大小，五行数据

    /** 5分时时彩赛果 */
    public static final String FIVESSC_RESULT_VALUE = "FIVESSC_RESULT_VALUE_1105";

    public static final String FIVESSC_NEXT_VALUE = "FIVESSC_NEXT_VALUE_1105";

    public static final String FIVESSC_ALGORITHM_VALUE = "FIVESSC_ALGORITHM_VALUE_1105";// 计算单双，大小，五行数据

    /** 德州时时彩赛果 */
    public static final String JSSSC_RESULT_VALUE = "JSSSC_RESULT_VALUE_1106";

    public static final String JSSSC_NEXT_VALUE = "JSSSC_NEXT_VALUE_1106";

    public static final String JSSSC_ALGORITHM_VALUE = "JSSSC_ALGORITHM_VALUE_1106";// 计算单双，大小，五行数据

    /** 六合彩赛果 */
    public static final String LHC_RESULT_VALUE = "LHC_RESULT_VALUE_1201";

    /** 1分六合彩赛果 */
    public static final String ONELHC_RESULT_VALUE = "ONELHC_RESULT_VALUE_1202";

    public static final String ONELHC_NEXT_VALUE = "ONELHC_NEXT_VALUE_1202";

    public static final String ONELHC_OPEN_VALUE = "ONELHC_OPEN_VALUE_1202";

    public static final String ONELHC_ALGORITHM_VALUE = "ONELHC_ALGORITHM_VALUE_1202";// 计算单双，大小，五行数据

    /** 5分六合彩赛果 */
    public static final String FIVELHC_RESULT_VALUE = "FIVELHC_RESULT_VALUE_1203";

    public static final String FIVELHC_NEXT_VALUE = "FIVELHC_NEXT_VALUE_1203";

    public static final String FIVELHC_OPEN_VALUE = "FIVELHC_OPEN_VALUE_1203";

    public static final String FIVELHC_ALGORITHM_VALUE = "FIVELHC_ALGORITHM_VALUE_1203";// 计算单双，大小，五行数据

    /** 时时六合彩赛果 */
    public static final String SSLHC_RESULT_VALUE = "SSLHC_RESULT_VALUE_1204";

    public static final String SSLHC_NEXT_VALUE = "SSLHC_NEXT_VALUE_1204";

    public static final String SSLHC_OPEN_VALUE = "SSLHC_OPEN_VALUE_1204";

    public static final String SSLHC_ALGORITHM_VALUE = "SSLHC_ALGORITHM_VALUE_1204";// 计算单双，大小，五行数据

    /** 北京PK10赛果 */
    public static final String BJPKS_RESULT_VALUE = "BJPKS_RESULT_VALUE_1301";

    public static final String BJPKS_NEXT_VALUE = "BJPKS_NEXT_VALUE_1301";

    public static final String BJPKS_OPEN_VALUE = "BJPKS_OPEN_VALUE_1301";

    public static final String BJPKS_ALGORITHM_VALUE = "BJPKS_ALGORITHM_VALUE_1301";// 计算单双，大小，五行数据

    /** 10分PK10赛果 */
    public static final String TENPKS_RESULT_VALUE = "TENPKS_RESULT_VALUE_1302";

    public static final String TENPKS_NEXT_VALUE = "TENPKS_NEXT_VALUE_1302";

    public static final String TENPKS_OPEN_VALUE = "TENPKS_OPEN_VALUE_1302";

    public static final String TENPKS_ALGORITHM_VALUE = "TENPKS_ALGORITHM_VALUE_1302";// 计算单双，大小，五行数据

    /** 5分PK10赛果 */
    public static final String FIVEPKS_RESULT_VALUE = "FIVEPKS_RESULT_VALUE_1303";

    public static final String FIVEPKS_NEXT_VALUE = "FIVEPKS_NEXT_VALUE_1303";

    public static final String FIVEPKS_OPEN_VALUE = "FIVEPKS_OPEN_VALUE_1303";

    public static final String FIVEPKS_ALGORITHM_VALUE = "FIVEPKS_ALGORITHM_VALUE_1303";// 计算单双，大小，五行数据

    /** 德州PK10赛果 */
    public static final String JSPKS_RESULT_VALUE = "JSPKS_RESULT_VALUE_1304";

    public static final String JSPKS_NEXT_VALUE = "JSPKS_NEXT_VALUE_1304";

    public static final String JSPKS_OPEN_VALUE = "JSPKS_OPEN_VALUE_1304";

    public static final String JSPKS_ALGORITHM_VALUE = "JSPKS_ALGORITHM_VALUE_1304";// 计算单双，大小，五行数据

    /** 幸运飞艇赛果 */
    public static final String XYFEIT_RESULT_VALUE = "XYFEIT_RESULT_VALUE_1401";

    public static final String XYFEIT_NEXT_VALUE = "XYFEIT_NEXT_VALUE_1401";

    public static final String XYFEIT_OPEN_VALUE = "XYFEIT_OPEN_VALUE_1401";

    public static final String XYFEIT_ALGORITHM_VALUE = "XYFEIT_ALGORITHM_VALUE_1401";// 计算单双，大小，五行数据

    /** PC蛋蛋赛果 */
    public static final String PCDAND_RESULT_VALUE = "PCDAND_RESULT_VALUE_1501";

    public static final String PCDAND_NEXT_VALUE = "PCDAND_NEXT_VALUE_1501";

    public static final String PCDAND_OPEN_VALUE = "PCDAND_OPEN_VALUE_1501";

    public static final String PCDAND_ALGORITHM_VALUE = "PCDAND_ALGORITHM_VALUE_1501";// 计算单双，大小，五行数据

    /** 比特币分分彩赛果 */
    public static final String TXFFC_RESULT_VALUE = "TXFFC_RESULT_VALUE_1601";

    public static final String TXFFC_NEXT_VALUE = "TXFFC_NEXT_VALUE_1601";

    public static final String TXFFC_OPEN_VALUE = "TXFFC_OPEN_VALUE_1601";

    public static final String TXFFC_ALGORITHM_VALUE = "TXFFC_ALGORITHM_VALUE_1601";// 计算单双，大小，五行数据

    /** 大乐透赛果 */
    public static final String DLT_RESULT_VALUE = "DLT_RESULT_VALUE_1701";

    public static final String DLT_NEXT_VALUE = "DLT_NEXT_VALUE_1701";

    /** 排列3/5赛果 */
    public static final String TCPLW_RESULT_VALUE = "TCPLW_RESULT_VALUE_1702";

    /** 7星彩赛果 */
    public static final String TC7XC_RESULT_VALUE = "TC7XC_RESULT_VALUE_1703";

    /** 双色球赛果 */
    public static final String FCSSQ_RESULT_VALUE = "FCSSQ_RESULT_VALUE_1801";

    /** 福彩3D赛果 */
    public static final String FC3D_RESULT_VALUE = "FC3D_RESULT_VALUE_1802";

    /** 七乐彩赛果 */
    public static final String FC7LC_RESULT_VALUE = "FC7LC_RESULT_VALUE_1803";

    /** 快乐牛牛赛果 */
    public static final String KLNIU_RESULT_VALUE = "KLNIU_RESULT_VALUE_1901";

    /** 澳洲牛牛赛果 */
    public static final String AZNIU_RESULT_VALUE = "AZNIU_RESULT_VALUE_1902";

    /** 德州牛牛赛果 */
    public static final String JSNIU_RESULT_VALUE = "JSNIU_RESULT_VALUE_1903";

    /** 德州PK10番摊赛果 */
    public static final String JSPKFT_RESULT_VALUE = "JSPKFT_RESULT_VALUE_2001";

    public static final String JSPKFT_NEXT_VALUE = "JSPKFT_NEXT_VALUE_2001";

    public static final String JSPKFT_OPEN_VALUE = "JSPKFT_OPEN_VALUE_2001";

    /** 幸运飞艇番摊赛果 */
    public static final String XYFTFT_RESULT_VALUE = "XYFTFT_RESULT_VALUE_2002";

    public static final String XYFTFT_NEXT_VALUE = "XYFTFT_NEXT_VALUE_2002";

    public static final String XYFTFT_OPEN_VALUE = "XYFTFT_OPEN_VALUE_2002";

    /** 德州时时彩番摊赛果 */
    public static final String JSSSCFT_RESULT_VALUE = "JSSSCFT_RESULT_VALUE_2003";

    public static final String JSSSCFT_NEXT_VALUE = "JSSSCFT_NEXT_VALUE_2003";

    /** 澳洲ACT赛果 */
    public static final String AUSACT_RESULT_VALUE = "AUSACT_RESULT_VALUE_2201";

    public static final String AUSACT_NEXT_VALUE = "AUSACT_NEXT_VALUE_2201";

    public static final String AUSACT_OPEN_VALUE = "AUSACT_OPEN_VALUE_2201";

    public static final String AUSACT_ALGORITHM_VALUE = "AUSACT_ALGORITHM_VALUE_2201";// 计算单双，大小，五行数据

    /** 澳洲时时彩赛果 */
    public static final String AUZSSC_RESULT_VALUE = "AUZSSC_RESULT_VALUE_2202";

    public static final String AUZSSC_NEXT_VALUE = "AUZSSC_NEXT_VALUE_2202";

    public static final String AUZSSC_OPEN_VALUE = "AUZSSC_OPEN_VALUE_2202";

    /** 澳洲澳洲F1赛果 */
    public static final String AUSPKS_RESULT_VALUE = "AUSPKS_RESULT_VALUE_2203";

    public static final String AUSPKS_NEXT_VALUE = "AUSPKS_NEXT_VALUE_2203";

    public static final String AUSPKS_OPEN_VALUE = "AUSPKS_OPEN_VALUE_2203";

    public static final String AUSPKS_ALGORITHM_VALUE = "AUSPKS_ALGORITHM_VALUE_2203";// 计算单双，大小，五行数据

    /**
     * ==================================WEB缓存============================================*
     */
    /** 六合彩心水推荐列表集合 */
    public static final String APP_GETLHCXSRECOMMENDS_RESULT_VALUE = "appGetlhcxsrecommends_result_value_";

    public static final String WEB_GETLHCXSRECOMMENDS_RESULT_VALUE = "webGetlhcxsrecommends_result_value_";

    /**
     * 私彩杀号配置
     */
    public static final String SICAIONELHCRATE = "SICAIONELHCRATE";
    public static final String SICAIONESSCRATE = "SICAIONESSCRATE";
    public static final String SICAIONEPKSRATE = "SICAIONEPKSRATE";
    public static final String SICAIFIVELHCRATE = "SICAIFIVELHCRATE";
    public static final String SICAIFIVESSCRATE = "SICAIFIVESSCRATE";
    public static final String SICAIFIVEPKSRATE = "SICAIFIVEPKSRATE";
    public static final String SICAITENLHCRATE = "SICAITENLHCRATE";
    public static final String SICAITENSSCRATE = "SICAITENSSCRATE";
    public static final String SICAITENPKSRATE = "SICAITENPKSRATE";
    public static final String SICAITXFFCRATE = "SICAITXFFCRATE";
    public static final String KILLORDERTIME = "KILLORDERTIME";

    /**************** 聊天室内容 *******************/
    public static final String CHAT_ROOM_VALUE = "CHAT_ROOM_VALUE_";
    public static final String CHAT_ROOM_MQTT_VALUE = "CHAT_ROOM_MQTT_VALUE_";
    public static final String CHAT_LOTTERY_VALUE = "CHAT_LOTTERY_VALUE";

    /********************** 支付设置 ************************/
    public static final String PAY_SET_VALUE = "PAY_SET_VALUE";

    /********************** 支付额度设置 ************************/
    public static final String PAY_QUOTA_VALUE = "PAY_QUOTA_VALUE";
    /** 人工充值默认额度 */
    public static final String PAY_QUOTA_MANUAL_DEFAULT_VALUE = "PAY_QUOTA_MANUAL_DEFAULT_VALUE";
    public static final String PAY_QUOTA_RECHARGE_ACCOUNT_PREFIX = "PAY_QUOTA_RECHARGE_ACCOUNT_";

    /********************** 支付方式设置 ************************/
    public static final String PAY_WAY_VALUE = "PAY_WAY_VALUE";
    public static final String PAY_WAY_LIST_VALUE = "PAY_WAY_LIST_VALUE";
    public static final String PAY_WAY_MAP_VALUE = "PAY_WAY_MAP_VALUE";

    /**
     * 下注结算
     */
    public static final String ORDER_LIST = "ORDER_";
    public static final long ORDER_TIME = 3600 * 24;
    public static final String BALANCECHANGE = "BALANCECHANGE_";

    /** 随机码 - 推广码 */
    public static final String SYSTEM_RANDOM_CODE_PROMOTION_REDISSON_LOCK_WRITE = "SYSTEM_RANDOM_CODE_PROMOTION_REDISSON_LOCK_WRITE";
    public static final String SYSTEM_RANDOM_CODE_PROMOTION_REDISSON_LOCK_READ = "SYSTEM_RANDOM_CODE_PROMOTION_REDISSON_LOCK_READ";
    public static final String SYSTEM_RANDOM_CODE_PROMOTION_REDISSON_LOCK_UPDATE = "SYSTEM_RANDOM_CODE_PROMOTION_REDISSON_LOCK_UPDATE";
    public static final String SYSTEM_RANDOM_CODE_CACHES_PREFIX = "SYSTEM_RANDOM_CODE_CACHES_";

    /** 缓存点赞 */
    public static final String MOBILE_ADMIRE_SUMNUMBER_ID = "mobile_admire_sumNumber_id_";
    /** 缓存取消点赞 */
    public static final String MOBILE_ADMIRE_CANCELNUMBER_ID = "mobile_admire_cancel_id_";
    /** 用户提现时缓存标记：USER_WITHDRAW_${user_id} */
    public static final String USER_WITHDRAW_PREFIX = "USER_WITHDRAW_";

    /** 六合彩图库爬取redis lock key */
    public static final String CRAWLER_LHC_TK_KJ_LOCK = "CRAWLER_LHC_TK_KJ_LOCK";
    /** 用户离线时间统计 */
    public static final String STAT_USER_OFFLINE_TIME_MAP = "STAT_USER_OFFLINE_TIME_MAP";

    /** 心水对于游客缓存结果数据 KEY */
    public static final String XSTJ_RECOMEND_DATE = "XSTJ_RECOMMEND_";

    /** 心水对于游客缓存帖子详细数据 KEY */
    public static final String XSTJ_RECOMEND_CONTENT = "XSTJ_RECOMMEND_CONTENT_";

    /**
     * 心水推荐缓存key
     */
    public static final String XS_CACHE_KEY_HASH = "XS_CACHE_KEY_HASH";

    /** 用户，收藏彩票 */
    public static final String LOTTERY_FAVORITE_USER_PREFIX = "LOTTERY_FAVORITE_USER_";
    /** 用户默认收藏彩票 */
    public static final String LOTTERY_FAVORITE_DEFAULT = "LOTTERY_FAVORITE_DEFAULT";
    /** 用户收藏的最终数据展示 */
    public static final String LOTTERY_FAVORITE_USER_DATA_PREFIX = "LOTTERY_FAVORITE_DATA_USER_";
    /** 用户默认收藏的最终数据展示 */
    public static final String LOTTERY_FAVORITE_USER_DATA_DEFAULT = "LOTTERY_FAVORITE_USER_DATA_DEFAULT";

    /** 结算id */
    public static final String ORDER_CLEAR = "ORDER_CLEAR_";

    /** 中奖推送 */
    public static final String WIN_PUSH = "WIN_PUSH_";

    /** 所有玩法设置信息 */
    public static final String LOTTERY_PLAY_SETTING_ALL_DATA = "LOTTERY_PLAY_SETTING_ALL_DATA";
    /** 所有玩法赔率信息 */
    public static final String LOTTERY_PLAY_ODDS_ALL_DATA = "LOTTERY_PLAY_ODDS_ALL_DATA";
    /** 所有玩法投注额限制 */
    public static final String RESTRICT_LIST_KEY = "RESTRICT_LIST_KEY";
    /** VIP 等级列表 */
    public static final String VIP_GRADE_LIST_KEY = "VIP_GRADE_LIST_KEY";

    /********************** 限制发帖子的KEY ************************/
    public static final String RECOMMEND_SEND_LIMIT_TIME = "LIMITSENDRECOMMEND";

    /** 充值账户列表 */
    public static final String RECHARGE_ACCOUNT_DTO_LIST_KEY = "RECHARGE_ACCOUNT_DTO_LIST_KEY";
    public static final String RECHARGE_ACCOUNT_LIST_KEY = "RECHARGE_ACCOUNT_LIST_KEY";
    public static final String RECHARGE_ACCOUNT_RECEIVE_UPDATE_KEY = "RECHARGE_ACCOUNT_RECEIVE_UPDATE_";

    public static final String APP_MEMBER_VIP_UPGRADE_LOCK_PREFIX = "APP_MEMBER_VIP_UPGRADE_LOCK_";
    public static final String APP_MEMBER_VIP_BACKWATER_LOCK_PREFIX = "APP_MEMBER_VIP_BACKWATER_LOCK_";
    public static final String APP_MEMBER_VIP_UPGRADE_LOCK = "APP_MEMBER_VIP_UPGRADE_LOCK";
    public static final String APP_MEMBER_VIP_BACKWATER_LOCK = "APP_MEMBER_VIP_BACKWATER_LOCK";

    /** 用户每日发送帖子数量统计 */
    public static final String USER_SEND_XSRECOMMEND_NUM = "USER_SEND_XSRECOMMEND_NUM";
    /** 用户每日帖子评论数量统计 */
    public static final String USER_SEND_RECOMMENDCOENT_NUM = "USER_SEND_RECOMMENDCOENT_NUM";
    /** 用户点赞帖子数量统计 */
    public static final String USER_ADMIRE_RECOMMEND_NUM = "USER_ADMIRE_RECOMMEND_NUM";
    /** 用户图片评论数量统计 */
    public static final String USER_SEND_PHOTODCOMENT_NUM = "USER_SEND_PHOTODCOMENT_NUM";

    public static final String PAY_SYSTEM_INFO = "PAY_SYSTEM_INFO";

    // 六和大神
    public static final String LHC_GOD_TYPE = "LHC_GOD_TYPE_";

    /** 校验用户获取验证码次数 Redis key */
    public static final String USER_CAPTCHA_CHECK_PREFIX = "USER_CAPTCHA_CHECK_";
    public static final String REDIS_CLEAR_TASK_KEY = "REDIS_CLEAR_TASK_KEY";

    public static final String METHOD_STAT_ENABLE = "_METHOD_STAT_ENABLE_";
    public static final String METHOD_STAT_PREFIX = "METHOD_STAT_";
    public static final String METHOD_STAT_IP_PREFIX = "METHOD_IP_STAT_";
    public static final String METHOD_STAT_TIME_PREFIX = "METHOD_TIME_STAT_";
    public static final String METHOD_STAT_TIME_IP_PREFIX = "METHOD_IP_TIME_STAT_";
    public static final String METHOD_STAT_TOTAL_IP_PREFIX = "METHOD_IP_TOTAL_STAT_";
}
