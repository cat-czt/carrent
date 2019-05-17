package com.qc.information.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: czt
 * @Date: 18-11-2 下午5:32
 */
public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * format date to String Example:2018-12-07 16:14:00
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }

    /**
     * format data to String Example: 2018-12-07
     * @param date
     * @return
     */
    public static String formatDateToShortStr(Date date) {
        return shortSdf.format(date);
    }

    /**
     * parse String to date, the pattern must be like this 2018-12-07 12:02:00
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) throws ParseException {
        synchronized (sdf) {
            return sdf.parse(strDate);
        }
    }

    /**
     * convert String to date according to the given pattern
     * @param pattern
     * @param aDate
     * @return
     */
    public static String convertDateToString(String pattern, Date aDate) {
        return getDateTime(pattern, aDate);
    }

    /**
     * get current date
     * @return
     */
    public static Date getSysDate() {
        Date sysDate = new Date();
        return sysDate;
    }

    /**
     *
     * @param aMask
     * @param aDate
     * @return
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 清除日期时间
     *
     * @param date
     */
    public static Date clearTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 添加年
     *
     * @param year
     * @return
     */
    public static Date addYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 添加月
     *
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    /**
     * 添加天数
     *
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 添加小时
     *
     * @param date
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    /**
     * 添加分
     *
     * @param date
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 添加秒
     */
    public static Date addSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 日期是否包括时间
     *
     * @param date
     * @return
     */
    public static boolean hasTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.HOUR) > 0) return true;
        if (cal.get(Calendar.MINUTE) > 0) return true;
        if (cal.get(Calendar.SECOND) > 0) return true;
        if (cal.get(Calendar.MILLISECOND) > 0) return true;
        return false;
    }

    /**
     * 得到日期天数
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        return getCalendar(date).get(Calendar.YEAR);
    }

    /**
     * 得到日期天数
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        return getCalendar(date).get(Calendar.MONTH) + 1;
    }

    /**
     * 得到日期天数
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        return getCalendar(date).get(Calendar.DATE);
    }

    /**
     * 得到小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        return getCalendar(date).get(Calendar.HOUR);
    }

    /**
     * 得到分
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        return getCalendar(date).get(Calendar.MINUTE);
    }

    /**
     * 得到秒
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        return getCalendar(date).get(Calendar.SECOND);
    }

    /**
     * 得到毫秒
     *
     * @param date
     * @return
     */
    public static int getMilliSecond(Date date) {
        return getCalendar(date).get(Calendar.MILLISECOND);
    }

    /**
     * 得到星期
     *
     * @return
     */
    public static int getWeek(Date date) {
        return getCalendar(date).get(Calendar.WEDNESDAY);
    }

    /**
     * 得到日期
     *
     * @return
     */
    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 得到日期
     *
     * @return
     */
    public static Date getDate(int year, int month, int day, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, 0, 0);
        return cal.getTime();
    }

    /**
     * 得到日期
     *
     * @return
     */
    public static Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, 0);
        return cal.getTime();
    }

    /**
     * 得到日期
     *
     * @return
     */
    public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, second);
        return cal.getTime();
    }

    /**
     * 得到总共的秒数
     *
     * @param d
     * @return
     */
    public static int getTimeTotalSecond(Date d) {
        Calendar cal = getCalendar(d);
        return cal.get(Calendar.HOUR) * 3600 + cal.get(Calendar.MINUTE) * 60 + cal.get(Calendar.SECOND);
    }

    /**
     * Date和String之间转换的函数：
     *
     * @param obj
     * @return
     */
    public static String getDateToString(Date obj) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//定义格式，不显示毫秒
        String str = df.format(obj);
        return str;
    }


    /**************************** 内部私有方法 *******************************/
    /**
     * Get Calendar
     * @param date
     * @return
     */
    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
}

