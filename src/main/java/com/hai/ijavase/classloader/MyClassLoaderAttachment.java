/**
 *
 */
package com.hai.ijavase.classloader;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.time.ZoneId;
import java.util.*;

/**
 * @author Administrator
 */
public class MyClassLoaderAttachment extends Date {

    private static final long serialVersionUID = 3241575516426379464L;

    /*
     * (non-Javadoc)
     *
     * @see java.util.Date#toString()
     */
    @Override
    public String toString() {
        return DateInfo.getDateInfo().getDetailInfo();
    }

    public static void main(String[] args) {
        System.out.println(new MyClassLoaderAttachment());

    }

    @Test
    public void testCalendar() {
//        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()), Locale.CHINA);
////        System.out.println("CALENDAR info:\r\n" + JSONObject.toJSONString(CALENDAR, true));
//        System.out.println("YEAR: " + calendar.get(Calendar.YEAR));
//        System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
//        System.out.println("DAY_OF_MONTH: " + calendar.get(Calendar.DAY_OF_MONTH));
//        System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
//        System.out.println("DAY_OF_WEEK_IN_MONTH: " + calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
//        System.out.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
//        System.out.println("WEEK_OF_MONTH: " + calendar.get(Calendar.WEEK_OF_MONTH));
//        System.out.println("WEEK_OF_YEAR: " + calendar.get(Calendar.WEEK_OF_YEAR));
//        System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
//        System.out.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
//        System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
//        System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
        System.out.println(JSONObject.toJSONString(DateInfo.getDateInfo(), true));
    }


    @Test
    public void testZoneId() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            String zoneId = iterator.next();
            if (zoneId.startsWith("Asia"))
                System.out.println(zoneId);
        }
    }

}

class DateInfo {
    private int year;
    private int month;
    private int dayOfMonth;
    private int dayOfWeek;
    private int dayOfWeekInMonth;
    private int dayOfYear;
    private int weekOfMonth;
    private int weekOfYear;
    private int hour;
    private int hourOfDay;
    private int minute;
    private int second;
    private int milliSecond;

    private static volatile DateInfo DATE_INFO = null;
    private static volatile Calendar CALENDAR = null;

    private DateInfo() {
    }

    public static DateInfo getDateInfo() {
        if (null == DATE_INFO) {
            synchronized (DateInfo.class) {
                if (null == DATE_INFO) {
                    DATE_INFO = new DateInfo();
                    CALENDAR = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.systemDefault()), Locale.CHINA);
                }
            }
        }
        DATE_INFO.setYear(CALENDAR.get(Calendar.YEAR));
        DATE_INFO.setMonth(CALENDAR.get(Calendar.MONTH) + 1);
        DATE_INFO.setDayOfMonth(CALENDAR.get(Calendar.DAY_OF_MONTH));
        DATE_INFO.setDayOfWeek(CALENDAR.get(Calendar.DAY_OF_WEEK));
        DATE_INFO.setDayOfWeekInMonth(CALENDAR.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        DATE_INFO.setDayOfYear(CALENDAR.get(Calendar.DAY_OF_YEAR));
        DATE_INFO.setWeekOfMonth(CALENDAR.get(Calendar.WEEK_OF_MONTH));
        DATE_INFO.setWeekOfYear(CALENDAR.get(Calendar.WEEK_OF_YEAR));
        DATE_INFO.setHour(CALENDAR.get(Calendar.HOUR));
        DATE_INFO.setHourOfDay(CALENDAR.get(Calendar.HOUR_OF_DAY));
        DATE_INFO.setMinute(CALENDAR.get(Calendar.MINUTE));
        DATE_INFO.setSecond(CALENDAR.get(Calendar.SECOND));
        DATE_INFO.setMilliSecond(CALENDAR.get(Calendar.MILLISECOND));
        return DATE_INFO;
    }

    public String getDetailInfo() {
        StringBuffer sb = new StringBuffer(32);
        sb.append(DATE_INFO.getYear()).append("-");
        sb.append(DATE_INFO.getMonthString()).append("-");
        sb.append(DATE_INFO.getDayOfMonth()).append(" ");
        sb.append(DATE_INFO.getHour()).append(":");
        sb.append(DATE_INFO.getMinute()).append(":");
        sb.append(DATE_INFO.getSecond()).append(".");
        sb.append(DATE_INFO.getMilliSecond());
        return sb.toString();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthString() {
        return month < 10 ? "0" + month : month + "";
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDayOfWeekInMonth() {
        return dayOfWeekInMonth;
    }

    public void setDayOfWeekInMonth(int dayOfWeekInMonth) {
        this.dayOfWeekInMonth = dayOfWeekInMonth;
    }

    public int getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(int dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public int getWeekOfMonth() {
        return weekOfMonth;
    }

    public void setWeekOfMonth(int weekOfMonth) {
        this.weekOfMonth = weekOfMonth;
    }

    public int getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(int weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getHourOfDay() {
        return hourOfDay;
    }

    public void setHourOfDay(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMilliSecond() {
        return milliSecond;
    }

    public void setMilliSecond(int milliSecond) {
        this.milliSecond = milliSecond;
    }
}
