package com.jasmine.springboot.util;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author xieshanghan
 * @version DateUtil.java, v 0.1 2023年02月03日 16:17 xieshanghan
 */
public class DateUtil {

    public static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Shanghai");

    public static final String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DAY_DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static final String HOUR_DATE_FORMAT_PATTERN = "HH:mm:ss";

    /**
     * 获取Jvm默认ZoneId
     *
     * @return
     */
    public static ZoneId getJvmDefaultZoneId() {
        return ZoneId.systemDefault();
    }

    /**
     * 获取Jvm默认ZoneId字符串
     *
     * @return
     */
    public static String getJvmDefaultZoneIdString() {
        return ZoneId.systemDefault().getId();
    }

    /**
     * Date转时间字符串
     *
     * @param date 具体日期
     * @return 默认时间默认格式时间字符串
     */
    public static String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_PATTERN);
        sdf.setTimeZone(DEFAULT_TIMEZONE);
        return sdf.format(date);
    }

    /**
     * Date转时间字符串
     *
     * @param date    具体日期
     * @param pattern 字符串格式
     * @return 默认时区指定格式时间字符串
     */
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(DEFAULT_TIMEZONE);
        return sdf.format(date);
    }

    /**
     * Date转时间字符串
     *
     * @param date     具体日期
     * @param pattern  字符串格式
     * @param timeZone 时区
     * @return 指定时区指定格式时间字符串
     */
    public static String date2String(Date date, String pattern, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(timeZone);
        return sdf.format(date);
    }

    /**
     * 时间字符串转Date
     *
     * @param dateString 时间字符串
     * @return 默认时区时间
     */
    public static Date string2Date(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT_PATTERN);
        sdf.setTimeZone(DEFAULT_TIMEZONE);
        try {
            return sdf.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时间字符串转Date
     *
     * @param dateString 时间字符串
     * @param pattern    字符串格式
     * @return 默认时区指定格式时间
     */
    public static Date string2Date(String dateString, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(DEFAULT_TIMEZONE);
        try {
            return sdf.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *
     *
     * @param dateString
     * @param pattern
     * @param timeZone
     * @return
     */
    public static Date string2Date(String dateString, String pattern, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(timeZone);
        try {
            return sdf.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时间字符串格式转换
     * 仅适用于默认格式时间字符串
     *
     * @param dateString
     * @param destPattern
     * @return
     */
    public static String dateStringConvert(String dateString, String destPattern) {
        Date date = string2Date(dateString, DEFAULT_DATE_FORMAT_PATTERN);
        return date2String(date, destPattern);
    }

    /**
     * 时间字符串格式转换
     *
     * @param dateString  时间字符串
     * @param sourPattern 原始格式
     * @param destPattern 目标格式
     * @return
     */
    public static String dateStringConvert(String dateString, String sourPattern, String destPattern) {
        Date date = string2Date(dateString, sourPattern);
        return date2String(date, destPattern);
    }

    /**
     * 时间比较
     *
     * @param date1 时间1
     * @param date2 时间2
     * @return
     */
    public static int timeCompare(Date date1, Date date2) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT_PATTERN);
            df.setTimeZone(DEFAULT_TIMEZONE);
            Date fromTime = df.parse(df.format(date1));
            Date toTime = df.parse(df.format(date2));
            if (fromTime.before(toTime)) {
                return -1;
            } else if (fromTime.equals(toTime)) {
                return 0;
            } else {
                return 1;
            }
        } catch (Throwable throwable) {
            throw new RuntimeException();
        }
    }

}