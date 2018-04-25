package com.example.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间处理类【存储系统所有的日期时间处理函数】
 * @author  Kevin Li
 * @date 2014年7月1日 上午10:32:42
 * @name DateUtils
 */
public class DateUtils {
    /**
     * 按照指定的格式，将日期类型对象转换成字符串，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
     * @param date
     * @param pattern 格式
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        return formater.format(date);
    }

    /**
     * 按照指定的格式，将字符串转换成日期类型对象，例如：yyyy-MM-dd,yyyy/MM/dd,yyyy/MM/dd hh:mm:ss
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        try {
            return formater.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @return 获取当前时间格式 yyyyMMddHHmmssms
     */
    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssms");
        return df.format(new Date());
    }

    /**
     * @return 获取当前时间格式 yyyyMMdd
     */
    public static String getDate() {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    /**
     * @return 获取上月日期格式 yyyy-MM
     */
    public static String getLastMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);

        return df.format(cal.getTime());
    }

    //全局的获取当前时间
    public static String getNowTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
        return sdf.format(date);
    }

    public static int getHour(long start, long end){
        long time = end - start;
        if(time <= 60 * 60 * 1000){
            return 1;
        } else {
            int hour = (int) (time / (1000 * 60 * 60));
            long temp = time % (1000 * 60 * 60);
            if(temp != 0){
                hour += 1;
            }
            return hour;
        }
    }

    //获取使用时间
    public static String getUseTime(long start, long end){
        int second = TimeZone.getDefault().getRawOffset();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");//初始化Formatter的转换格式。
        return formatter.format((end - start - second));
    }

}
