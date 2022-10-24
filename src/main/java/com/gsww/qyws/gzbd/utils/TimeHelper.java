package com.gsww.qyws.gzbd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>
 * 日期帮助类
 * </p>
 *
 * @author lixin
 * @version V2.0.0
 * @company 中国电信甘肃万维公司
 * @project jup-util
 * @date 2012-6-19 下午05:01:04
 * @class com.gsww.jup.util.TimeHelper
 */
public class TimeHelper {
    private static String CurrentTime;

    private static String CurrentDate;

    /**
     * 得到当前的年份 返回格式:yyyy
     *
     * @return String
     */
    public static String getCurrentYear() {
        Date NowDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的月份 返回格式:MM
     *
     * @return String
     */
    public static String getCurrentMonth() {
        Date NowDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的日期 返回格式:dd
     *
     * @return String
     */
    public static String getCurrentDay() {
        Date NowDate = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(NowDate);
    }

    /**
     * 得到当前的时间，精确到毫秒,共19位 返回格式:yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String getCurrentTime() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    public static String getCurrentCompactTime() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    public static String getYesterdayCompactTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.DATE, -1);
        System.out.print(cal.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        CurrentTime = formatter.format(cal.getTime());
        return CurrentTime;
    }

    public static String getYesterdayCompactTimeForFileName() {
        Calendar cal = Calendar.getInstance();
        cal.add(cal.DATE, -1);
        System.out.print(cal.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CurrentTime = formatter.format(cal.getTime());
        return CurrentTime;
    }

    /**
     * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentDate() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    /**
     * 得到当前的日期,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentDate1() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    /**
     * 得到当月的第一天,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     */
    public static String getCurrentFirstDate() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    /**
     * 得到当月的最后一天,共10位 返回格式：yyyy-MM-dd
     *
     * @return String
     * @throws ParseException
     */
    public static String getCurrentLastDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = null;
        try {
            Date date = formatter.parse(getCurrentFirstDate());
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_YEAR, -1);
            return formatDate(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 常用的格式化日期
     *
     * @param date Date
     * @return String
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * 以指定的格式来格式化日期
     *
     * @param date   Date
     * @param format String
     * @return String
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表天数 输入参数：currentdate : String 格式 yyyy-MM-dd add_day :
     * int 返回格式：yyyy-MM-dd
     */
    public static String addDay(String currentdate, int add_day) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int year, month, day;

        try {
            year = Integer.parseInt(currentdate.substring(0, 4));
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdate.substring(8, 10));

            gc = new GregorianCalendar(year, month, day);
            gc.add(GregorianCalendar.DATE, add_day);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前日期加上某一个整数的日期，整数代表月数 输入参数：currentdate : String 格式 yyyy-MM-dd add_month :
     * int 返回格式：yyyy-MM-dd
     */
    public static String addMonth(String currentdate, int add_month) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int year, month, day;

        try {
            year = Integer.parseInt(currentdate.substring(0, 4));
            month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdate.substring(8, 10));

            gc = new GregorianCalendar(year, month, day);
            gc.add(GregorianCalendar.MONTH, add_month);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到endTime比beforeTime晚几个月，整数代表月数 输入参数：endTime、beforeTime : String 格式前7位的格式为 yyyy-MM
     */
    public static int monthDiff(String beforeTime, String endTime) {
        if (beforeTime == null || endTime == null) {
            return 0;
        }
        int beforeYear, endYear, beforeMonth, endMonth;
        try {
            beforeYear = Integer.parseInt(beforeTime.substring(0, 4));
            endYear = Integer.parseInt(endTime.substring(0, 4));
            beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1;
            endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1;
            return (endYear - beforeYear) * 12 + (endMonth - beforeMonth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到当前日期加上某一个整数的分钟 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
     * add_minute : int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    public static String addMinute(String currentdatetime, int add_minute) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4));
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdatetime.substring(8, 10));

            hour = Integer.parseInt(currentdatetime.substring(11, 13));
            minute = Integer.parseInt(currentdatetime.substring(14, 16));
            second = Integer.parseInt(currentdatetime.substring(17, 19));

            gc = new GregorianCalendar(year, month, day, hour, minute, second);
            gc.add(GregorianCalendar.MINUTE, add_minute);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到当前日期加上某一个整数的秒 输入参数：currentdatetime : String 格式 yyyy-MM-dd HH:mm:ss
     * add_second : int 返回格式：yyyy-MM-dd HH:mm:ss
     */
    public static String addSecond(String currentdatetime, int add_second) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4));
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdatetime.substring(8, 10));

            hour = Integer.parseInt(currentdatetime.substring(11, 13));
            minute = Integer.parseInt(currentdatetime.substring(14, 16));
            second = Integer.parseInt(currentdatetime.substring(17, 19));

            gc = new GregorianCalendar(year, month, day, hour, minute, second);
            gc.add(GregorianCalendar.SECOND, add_second);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String addMinute1(String currentdatetime, int add_minute) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        int year, month, day, hour, minute, second;

        try {
            year = Integer.parseInt(currentdatetime.substring(0, 4));
            month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
            day = Integer.parseInt(currentdatetime.substring(8, 10));

            hour = Integer.parseInt(currentdatetime.substring(8, 10));
            minute = Integer.parseInt(currentdatetime.substring(8, 10));
            second = Integer.parseInt(currentdatetime.substring(8, 10));

            gc = new GregorianCalendar(year, month, day, hour, minute, second);
            gc.add(GregorianCalendar.MINUTE, add_minute);

            return formatter.format(gc.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseDate(String sDate) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = bartDateFormat.parse(sDate);
            return date;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * 解析日期及时间
     *
     * @param sDateTime 日期及时间字符串
     * @return 日期
     */
    public static Date parseDateTime(String sDateTime) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");

        try {
            Date date = bartDateFormat.parse(sDateTime);
            return date;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * 取得一个月的天数 date:yyyy-MM-dd
     *
     * @throws ParseException
     */
    public static int getTotalDaysOfMonth(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();

        Date date = new Date();
        try {
            date = sdf.parse(strDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        calendar.setTime(date);
        //int month = calendar.get(Calendar.MONTH) + 1; // 月份
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 天数
        return day;
    }


    public static long getDateSubDay(String startDate, String endDate) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long theday = 0;
        try {
            calendar.setTime(sdf.parse(startDate));
            long timethis = calendar.getTimeInMillis();
            calendar.setTime(sdf.parse(endDate));
            long timeend = calendar.getTimeInMillis();
            theday = (timethis - timeend) / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theday;
    }

    /**
     * * 获得指定日期的前一天 *
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            c.setTime(date);
        }

        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            c.setTime(date);
        }

        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 获得两个日期之间的月
     *
     * @param specifiedDay
     * @return
     */
    public static int getMonthSpace(String startTime, String endTime)
            throws ParseException {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(startTime));
        c2.setTime(sdf.parse(endTime));

        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);

        return result == 0 ? 1 : Math.abs(result);

    }

    /**
     * 获取当前日期的制定月份
     *
     * @param i 默认i是1是当前月份
     * @return
     */
    public static String getBeforeMonth(int i) {
        String mh = "";
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + i;
        if (String.valueOf(month).length() > 1) {
            mh = String.valueOf(month);
        } else {
            mh = "0" + String.valueOf(month);
        }
        return mh;
    }

    public static String getBeforeCurrentDate(int i) {
        String preMonth = null;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, i);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        preMonth = dateFormat.format(c.getTime());
        return preMonth;
    }

    public static String getPerFirstDayOfMonth(Calendar calendar) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

    public static void main(String[] args) {

    }

    public static Date getTodayZreo() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        Date beginOfDate = calendar1.getTime();
        return beginOfDate;
    }

    /**
     * 获取昨天日期日 yyyy-MM-dd
     *
     * @return
     */
    public static String getYesterdayDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date d = cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        return sp.format(d);//获取昨天日期
    }

    public static String getHours8(){
                Calendar rightNow = Calendar.getInstance();
       int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        String upDate = TimeHelper.getYesterdayDate();//默认查询日期为昨天
        //如果大于晚上20时则查询今天的日期
        if (hour >= 20) {
            upDate = TimeHelper.getCurrentDate();
        }
        return upDate;
    }
}
