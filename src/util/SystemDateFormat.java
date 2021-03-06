package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemDateFormat {

    /**
     * @param []
     * @Description: 返回本机系统的yyyy-mm-dd类型的时间
     * @Return java.lang.String
     */
    public static String getSystemDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateTime = df.format(new Date());
        return dateTime;
    }

    /**
     * @param []
     * @Description: 返回本机系统的更精确的yyyy-mm-dd HH:mm:ss类型的时间
     * @Return java.lang.String
     */
    public static String getSystemPreciseDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date());
        return dateTime;
    }

    public static String getSystemPreciseDate1() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateTime = df.format(new Date());
        return dateTime;
    }

    public static String getSystemPreciseDate2(java.sql.Timestamp date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(new Date(date.getTime()));
        return dateTime;
    }

    public static java.sql.Date reSqlDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    /**
     * @Description: 返回本机系统加5分钟的更精确的yyyy-mm-dd HH:mm:ss类型的时间
     * @param []
     * @Return java.lang.String
     */
    public static String getSystemPreciseDate5m() {
        long currentTimeMillis = System.currentTimeMillis();
        currentTimeMillis += 5 * 60 * 1000;
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(date);
        return dateTime;
    }

    /**
     * @Description: 参数1时间是否小于参数2
     * @param [date, modelDate]
     * @Return boolean
     */
    public static boolean isDateLess(String date, String modelDate) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date dateFront = null;
        Date dateAfter = null;
        try {
            dateFront = dateFormat.parse(date);
            dateAfter = dateFormat.parse(modelDate);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (dateFront.getTime() < dateAfter.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Description: 返回相差时间（天）
     * @param [regDate, nowDate]
     * @Return long
     */
    public static long daySub(String regDate, String nowDate) throws ParseException {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return (dateFormat.parse(nowDate).getTime() - dateFormat.parse(regDate).getTime()) / 1000 / 60 / 60 / 24;
    }


}
