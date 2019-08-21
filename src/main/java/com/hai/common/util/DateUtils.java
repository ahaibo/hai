package com.hai.common.util;

import com.hai.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /** 星期一 */
    public static final int MONDAY = 1;
    /** 星期二 */
    public static final int TUESDAY = 2;
    /** 星期三 */
    public static final int WEDNESDAY = 3;
    /** 星期四 */
    public static final int THURSDAY = 4;
    /** 星期五 */
    public static final int FRIDAY = 5;
    /** 星期六 */
    public static final int SATURDAY = 6;
    /** 星期天 */
    public static final int SUNDAY = 7;


    //日期格式模板
    public static final String FORMAT_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DEFAULT_TIMESTAMP = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
    public static final String timePattern = "HH:mm:ss";
    public static final String HOURANDMINUTES = "HH:mm";
    public static final String TIME_FORMAT = "HH:mm:ss:SS";
    public static final String DEFAULT_SHORT_DATE_FORMAT_ZH = "yyyy年M月d日";
    public static final String DEFAULT_LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SS";
    public static final String JAVA_MIN_SHORT_DATE_STR = "1970-01-01";
    public static final String JAVA_MIN_LONG_DATE_STR = "1970-01-01 00:00:00:00";
    public static final String DEFAULT_PERIOD_FORMAT = "{0}天{1}小时{2}分钟";
    public static final String JAVA_MAX_SHORT_DATE_STR = "9999-12-31";
    public static final String TIMEZERO = "00:00:00";
    public static final String TIMEZERO_BLANK = " 00:00:00";
    public static final String XYFT_OPEN_TIME = "04:04:00";
    public static final String XYFT_START_TIME = "13:09:00";
    public static final String XYFT_MID_TIME = " 13:09:00";
    public static final String TIMEOVER = "23:59:00";
    public static final String TIMEOVER_BLANK = " 23:59:00";
    public static final String TIMEOVERHOUR = "23:59";

    /**
     * 获取下一期官方开奖时间
     *
     * @param dateTime 当前期官方开奖时间
     * @return
     */
    public static Date nextIssueTime(Date dateTime) {
        return DateUtils.getMinuteAfter(dateTime, 1);
    }

    public static String formatDate(Date date) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
            return df.format(date);
        }
        return "";
    }

    /**
     * 格式化时间
     *
     * @param date    时间
     * @param pattern 格式化字符串
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return "";
    }

    /**
     * @Title: getTodayStart
     * @Description: 获取当天的开始时间
     * @date 2019年7月18日下午2:39:39
     */
    public static Date getTodayStart() {
        String todayTime = formatDate(new Date(), DateUtils.FORMAT_YYYY_MM_DD);
        String todayStartTime = todayTime + " 00:00:00";
        Date todayStartData = parseDate(todayStartTime);
        return todayStartData;
    }

    /**
     * 将时间对象的日期部分格化化
     *
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        return formatDate(date, FORMAT_YYYY_MM_DD);
    }

    /**
     * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
     *
     * @param date   要加减前的时间，如果不传，则为当前日期
     * @param field  时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE, *
     *               Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
     * @param amount 按指定时间域加减的时间数量，正数为加，负数为减。
     * @return 变动后的时间
     */
    public static Date add(Date date, int field, int amount) {
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 给时间加上或减去指定毫秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliSeconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    public static Date addMilliSeconds(String date, int amount) {
        return addMilliSeconds(date, amount, null);
    }

    public static Date addMilliSeconds(String date, int amount, String pattern) {
        return addMilliSeconds(parseDate(date, getDefaultFormatPattern(date, pattern)), amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    public static Date addSeconds(String date, int amount) {
        return addSeconds(date, amount, null);
    }

    public static Date addSeconds(String date, int amount, String pattern) {
        return addSeconds(parseDate(date, getDefaultFormatPattern(date, pattern)), amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, Calendar.MINUTE, amount);
    }

    public static Date addMinutes(String date, int amount) {
        return addMinutes(date, amount, null);
    }

    public static Date addMinutes(String date, int amount, String pattern) {
        return addMinutes(parseDate(date, getDefaultFormatPattern(date, pattern)), amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    public static Date addHours(String date, int amount) {
        return addHours(date, amount, null);
    }

    public static Date addHours(String date, int amount, String pattern) {
        return addHours(parseDate(date, getDefaultFormatPattern(date, pattern)), amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    public static Date addDays(String date, int amount) {
        return addDays(date, amount, null);
    }

    public static Date addDays(String date, int amount, String pattern) {
        return addDays(parseDate(date, getDefaultFormatPattern(date, pattern)), amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    public static Date addMonths(String date, int amount) {
        return addMonths(date, amount, null);
    }

    public static Date addMonths(String date, int amount, String pattern) {
        return addMonths(parseDate(date, getDefaultFormatPattern(date, pattern)), amount);
    }

    public static Date addYears(Date date, int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    public static Date addYears(String date, int amount) {
        return addYears(date, amount, null);
    }

    public static Date addYears(String date, int amount, String pattern) {
        return addYears(parseDate(date, getDefaultFormatPattern(date, pattern)), amount);
    }

    /**
     * 添加一年
     *
     * @param date
     * @return
     */
    public static Date addOneYears(Date date) {
        return addYears(date, 1);
    }

    /**
     * 将时间对象的日期、时间部分格化化
     *
     * @param date
     * @return
     */
    public static String getFullString(Date date) {
        return formatDate(date, FORMAT_YYYY_MM_DD_HHMMSS);
    }

    public static String getFullsString(Date date) {
        return formatDate(date, DEFAULT_TIMESTAMP);
    }

    /**
     * 将时间对象的时间部分格化化
     *
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        return formatDate(date, timePattern);
    }

    /**
     * 将时间对象的时间部分格化化
     *
     * @param date
     * @return
     */
    public static String getTimeString(Date date, String pattern) {
        return formatDate(date, pattern);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     *
     * @param patterns 输入字符串的格式
     * @param str      一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     */
    public static Date parseDate(String str, String[] patterns) {
        for (String pattern : patterns) {
            try {
                SimpleDateFormat df = new SimpleDateFormat(pattern);
                return df.parse(str);
            } catch (Exception e) {
                logger.error("parseDate occur error for str:{}, error:{}", str, e);
            }
        }
        return null;
    }

    public static Date parseDate(String str) {
        return parseDate(str, FORMAT_YYYY_MM_DD_HHMMSS);
    }

    /**
     * 按照日期格式，将字符串解析为日期对象
     *
     * @param pattern 输入字符串的格式
     * @param str     一个按aMask格式排列的日期的字符串描述
     * @return Date 对象
     */
    public static Date parseDate(String str, String pattern) {
        if (null == str || "".equals(str.trim()) || "0".equals(str)) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(str);
        } catch (Exception e) {
            logger.error("parseDate occur error for str:{}, error:{}", str, e);
            return null;
        }
    }

    /**
     * 将<code>datePattern<code>为格式的字符串解析为日期对象
     *
     * @param str
     * @return
     */
    public static Date dateStringToDate(String str) {
        try {
            return parseDate(str, FORMAT_YYYY_MM_DD);
        } catch (Exception e) {
            logger.error("dateStringToDate occur error for str:{}, error:{}", str, e);
            return null;
        }
    }

    /**
     * 将<code>datePattern<code>为格式的字符串解析为日期对象
     *
     * @param str
     * @return
     */
    public static Date dateStringToDate(String str, String pattern) {
        return parseDate(str, pattern);
    }

    /**
     * add by fa ,2008.12.11 将日期对象转换为“yyyyMMdd”String
     *
     * @param date
     * @return String
     */
    public static String dateToShortString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }

    public static Date addDate(String datepart, int number, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (datepart.equals("yy")) {
            cal.add(Calendar.YEAR, number);
        } else if (datepart.equals("MM")) {
            cal.add(Calendar.MONTH, number);
        } else if (datepart.equals("dd")) {
            cal.add(Calendar.DAY_OF_MONTH, number);
        } else if (datepart.equals("HH")) {
            cal.add(Calendar.HOUR_OF_DAY, number);
        } else if (datepart.equals("mm")) {
            cal.add(Calendar.MINUTE, number);
        } else if (datepart.equals("ss")) {
            cal.add(Calendar.SECOND, number);
        } else {
            throw new IllegalArgumentException("DateUtil.addDate()方法非法参数值：" + datepart);
        }

        return cal.getTime();
    }

    public static boolean compareNow(String time1) {
        return compareTime(time1, currentStr(), FORMAT_YYYY_MM_DD_HHMMSS);
    }

    public static boolean compareTime(String time1, String time2) {
        return compareTime(time1, time2, FORMAT_YYYY_MM_DD_HHMMSS);
    }

    public static boolean compareTime(String time1, String time2, String dateFormat) {
        SimpleDateFormat t1 = new SimpleDateFormat(dateFormat);
        SimpleDateFormat t2 = new SimpleDateFormat(dateFormat);
        try {
            Date d1 = t1.parse(time1);
            Date d2 = t2.parse(time2);
            return d1.before(d2);
        } catch (Exception e) {
            logger.error("compareTime occur error for time1:{}, time2:{}, error:{}", time1, time2, e);
            throw new RuntimeException(e);
        }
    }

    public static Date convert(String date, String format) {
        if ((date == null) || (date.equals(""))) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            logger.error("convert occur error for date:{}, format:{}, error:{}", date, format, e);
            throw new RuntimeException("DateUtil.convert():" + e.getMessage());
        }
    }

    public static String convert(Date date, String format) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date convert(String date) {
        return convert(date, getDefaultFormatPattern(date));
    }

    public static String getDefaultFormatPattern(String dateString) {
        return getDefaultFormatPattern(dateString, null);
    }

    public static String getDefaultFormatPattern(String dateString, String pattern) {
        if (null == pattern || "".equals(pattern)) {
            pattern = dateString.length() < 11 ? FORMAT_YYYY_MM_DD : FORMAT_YYYY_MM_DD_HHMMSS;
        }
        return pattern;
    }

    public static Date convert(long time) {
        return new Date(time);
    }

    public static Date convert(Long time) {
        return new Date(time.longValue());
    }

    public static String convert(Date date) {
        return convert(date, FORMAT_YYYY_MM_DD_HHMMSS);
    }

    public static Date toDate(Object o) {
        if (null == o) {
            return null;
        }

        if (o instanceof Date) {
            return ((Date) o);
        }

        if (o instanceof String) {
            return convert((String) o);
        }

        if (o instanceof Long) {
            Long t = (Long) o;
            return new Date(t.longValue());
        }
        throw new RuntimeException("invalid time object:" + o);
    }

    public static String format(long period) {
        long dayUnit = 86400L;
        long hourUnit = 3600L;
        long minUnit = 60L;
        String result = MessageFormat.format(DEFAULT_PERIOD_FORMAT, new Object[]{Long.valueOf(period / dayUnit),
                Long.valueOf(period % dayUnit / hourUnit), Long.valueOf(period % hourUnit / minUnit)});
        return result;
    }

    public static double dateDiff(String datepart, Date startdate, Date enddate) {
        if ((datepart == null) || ("".equals(datepart))) {
            String info = "DateUtil.dateDiff()方法非法参数值：" + datepart;
            throw new IllegalArgumentException(info);
        }

        double days = (enddate.getTime() - startdate.getTime()) / 86400000.0D;

        if (datepart.equals("yy")) {
            days /= 365.0D;
        } else if (datepart.equals("MM")) {
            days /= 30.0D;
        } else {
            if (datepart.equals("dd")) {
                return days;
            }

            String info = "DateUtil.dateDiff()方法非法参数值：" + datepart;
            throw new IllegalArgumentException(info);
        }
        return days;
    }

    /**
     * 计算时间差
     *
     * @param time1
     * @param time2
     * @return
     */
    public static long timeReduce(String time1, String time2) {
        DateFormat df = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
        try {
            Date t1 = df.parse(time1);
            Date t2 = df.parse(time2);
            return (t1.getTime() - t2.getTime()) / 1000l;
        } catch (ParseException e) {
            logger.error("parseDate occur error for time1:{}, time2:{} error:{}", time1, time2, e);
        }
        return 0;
    }

    public static String currentStr() {
        return currentStr(FORMAT_YYYY_MM_DD_HHMMSS);
    }

    public static String currentStr(String dateFormat) {
        return convert(new Date(), dateFormat);
    }

    public static long getTimeMillis(String dateStr) {

        if (StringUtils.isEmpty(dateStr)) {
            return Constants.DEFAULT_LONG;
        }
        return convert(dateStr, FORMAT_YYYY_MM_DD_HHMMSS).getTime();
    }

    public static Date getFirstMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMinimum(5));
        return calendar.getTime();
    }

    public static Date getFirstDayNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(2, 1);
        return calendar.getTime();
    }

    public static Date getFirstDayCurMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getFirstDayCurYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(6, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getFirstDayCurYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, 1, 1);
        calendar.set(6, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getLastMonthDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        return calendar.getTime();
    }

    public static String addDate(String datepart, int number, String dateStr) {
        Date dt = convert(dateStr);
        Date date = addDate(datepart, number, dt);
        return convert(date);
    }

    public static String formartTime(Long time) {
        Long hour = time / 3600;
        Long temp = time % 3600;
        Long minute = temp / 60;
        Long second = temp % 60;
        return (hour > 0 ? hour + "小时，" : "") + (minute > 0 ? minute + "分，" : "") + (second + "秒");
    }

    public static int getDateInterval(String day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.convert(day));
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.setTime(getLastMonthDay(DateUtils.convert(day)));
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return dd - d;
    }

    public static int getDayOfMonth(String day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.convert(day));
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        return d;
    }

    public static int getLocalMothDays() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getLastMonthDay(new Date()));
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间后多少分钟的时间
     *
     * @param date  制定的时间
     * @param count 后几分钟
     * @return
     */
    public static Date getMinuteAfter(Date date, Integer count) {
        long time = date.getTime();
        long diff = count * 60 * 1000;
        long nowTime = time + diff;
        return new Date(nowTime);
    }

    /**
     * 获取时间后多少秒钟的时间
     *
     * @param date  制定的时间
     * @param count 后几秒钟
     * @return
     */
    public static Date getSecondAfter(Date date, Integer count) {
        long time = date.getTime();
        long diff = count * 1000;
        long nowTime = time + diff;
        return new Date(nowTime);
    }

    /**
     * 获取指定时间前后多少天的时间
     *
     * @param date  指定的时间
     * @param count 前后多少天
     * @return
     */
    public static Date getDayAfter(Date date, Long count) {
        long time = date.getTime();
        long diff = count * 24 * 60 * 60 * 1000;
        long nowTime = time + diff;
        return new Date(nowTime);
    }

    /**
     * 获取当天开始时间
     *
     * @param date 日期
     * @return
     */
    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @param date 日期
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @param date 日期
     * @return
     */
    public static Date getDayBegin(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(DateUtils.parseDate(date, FORMAT_YYYYMMDD)));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当天开始时间
     *
     * @param date 日期
     * @return
     */
    public static Date getDayEnd(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Objects.requireNonNull(DateUtils.parseDate(date, FORMAT_YYYYMMDD)));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 给指定时间设置时分秒
     *
     * @param date        日期
     * @param hour        时
     * @param minute      分
     * @param second      秒
     * @param millisecond 毫秒
     * @return
     */
    public static Date setTime(Date date, Integer hour, Integer minute, Integer second, Integer millisecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar.getTime();
    }

    /**
     * 计算两个日期相差的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        // 不同年
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { // 同年
            return day2 - day1;
        }
    }

    /**
     * 按时间判断是星期几
     *
     * @param pTime 日期
     * @return
     * @throws Exception
     */
    public static int dayForWeek(String pTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYYMMDD);
        Calendar c = Calendar.getInstance();
        c.setTime(format.parse(pTime));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取本周一的日期
     *
     * @return
     */
    public static Date getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 1);
        return c.getTime();
    }

    /**
     * 获取本周末的日期
     *
     * @return
     */
    public static Date getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
        }
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }

    /**
     * 获取上个月的第一天
     *
     * @return
     */
    public static Date getFirstOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取上个月的最后一天
     *
     * @return
     */
    public static Date getLastOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 计算当前时间是周几,周日返回 7
     *
     * @return
     */
    public static int getCurrentWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 验证字符是否为有效时间
     *
     * @param str
     * @return
     */
    public static boolean isValidDateTime(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIMESTAMP);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 根据日期获取当年总天数
     *
     * @param date
     * @return
     */
    public static int getYearsDays(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    public static String getTestLong() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, 30);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
        String dateStr = sdf.format(now.getTimeInMillis());
        return dateStr;
    }

    /**
     * @Title: pastWeek
     * @Description: 一周前时间
     * @author HANS
     * @date 2019年5月22日下午2:40:14
     */
    public static String pastWeek() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
        Calendar calendar = Calendar.getInstance();
        // 过去七天
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -7);
        Date date = calendar.getTime();
        String day = format.format(date);
        return day;
    }

    /**
     * @Title: pastWeek
     * @Description: 半个月前时间
     * @author HANS
     * @date 2019年5月22日下午2:40:14
     */
    public static String pastHalfMonth() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
        Calendar calendar = Calendar.getInstance();
        // 过去半个月
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -15);
        Date date = calendar.getTime();
        String day = format.format(date);
        return day;
    }

    /**
     * @Title: pastWeek
     * @Description: 半个月前时间
     * @author HANS
     * @date 2019年5月22日下午2:40:14
     */
    public static String pastHalfMonth(Date thisTime) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
        Calendar calendar = Calendar.getInstance();
        // 过去半个月
        calendar.setTime(thisTime);
        calendar.add(Calendar.DATE, -15);
        Date date = calendar.getTime();
        String day = format.format(date);
        return day;
    }

    /**
     * @Title: pastMonth
     * @Description: 获取三月前时间
     * @author HANS
     * @date 2019年5月22日下午2:43:17
     */
    public static Date pastMonth() {
        Calendar calendar = Calendar.getInstance();
        // 过去一月
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -6);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * @Title: pastMonth
     * @Description: 获取一周前时间
     * @author HANS
     * @date 2019年5月22日下午2:43:17
     */
    public static Date pastOneHourAgo() {
        Calendar calendar = Calendar.getInstance();
        // 1小时前
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, -3);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * @Title: pastYear
     * @Description: 获取一年前时间
     * @author HANS
     * @date 2019年5月22日下午2:43:51
     */
    public static String pastYear() {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HHMMSS);
        Calendar calendar = Calendar.getInstance();
        // 过去一年
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        Date date = calendar.getTime();
        String year = format.format(date);
        return year;
    }

    public static String getCurrentDate() {
        String formatPattern_Short = FORMAT_YYYYMMDDHHMMSS;
        SimpleDateFormat format = new SimpleDateFormat(formatPattern_Short);
        return format.format(new Date());
    }

    public static String getSeqString() {
        SimpleDateFormat fm = new SimpleDateFormat(DEFAULT_TIMESTAMP); // "yyyyMMdd G
        return fm.format(new Date());
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date str2date(String str, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            logger.error("str2date occur error.", e);
        }
        return date;
    }

    public static Date str2date(String str) {
        return str2date(str, FORMAT_YYYY_MM_DD_HHMMSS);
    }

    /**
     * 获取当前时间，格式为 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentTimeStr(String format) {
        format = org.apache.commons.lang3.StringUtils.isBlank(format) ? FORMAT_YYYY_MM_DD_HHMMSS : format;
        Date now = new Date();
        return date2Str(now, format);
    }

    public static String date2Str() {
        return date2Str(FORMAT_YYYY_MM_DD);
    }

    public static String date2Str(String format) {
        return date2Str(new Date(), format);
    }

    public static String date2Str(Date date) {
        return date2Str(date, FORMAT_YYYY_MM_DD_HHMMSS);
    }

    public static String todayStartTimeString() {
        return DateUtils.date2Str() + " " + TIMEZERO;
    }

    public static String todayEndTimeString() {
        return DateUtils.date2Str() + " " + TIMEOVER;
    }

    /**
     * 获取当天开始时间
     *
     * @return
     */
    public static Date todayStartTime() {
        return DateUtils.parseDate(DateUtils.date2Str() + " " + TIMEZERO, DateUtils.FORMAT_YYYY_MM_DD_HHMMSS);
    }

    /**
     * 获取当天结束时间
     *
     * @return
     */
    public static Date todayEndTime() {
        return DateUtils.parseDate(DateUtils.date2Str() + " " + TIMEOVER, DateUtils.FORMAT_YYYY_MM_DD_HHMMSS);
    }

    /**
     * 时间转换成 Date 类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format) {
        if ((format == null) || format.equals("")) {
            format = FORMAT_YYYY_MM_DD_HHMMSS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (date != null) {
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 获取批量付款预约时间
     *
     * @return
     */
    public static String getRevTime() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        String dateString = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS).format(cal.getTime());
        logger.info(dateString);
        return dateString;
    }

    /**
     * 时间比较
     *
     * @param date1
     * @param date2
     * @return DATE1>DATE2返回1，DATE1<DATE2返回-1,等于返回0
     */
    public static int compareDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            logger.error("compareDate occur error for date1:{}, date2:{}, format:{}, error:{}", date1, date2, format, e);
        }
        return 0;
    }

    /**
     * 把给定的时间减掉给定的分钟数
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date minusDateByMinute(Date date, int minute) {
        Date newDate = new Date(date.getTime() - (minute * 60 * 1000));
        return newDate;
    }

    /**
     * 当天凌晨0点
     *
     * @return
     */
    public static String dayBeginStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return formatDate(calendar.getTime());
    }

    /**
     * 当天23:59:59
     *
     * @return
     */
    public static String dayEndStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return formatDate(calendar.getTime());
    }

}
