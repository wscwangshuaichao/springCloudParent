package springcloud.learn.wsc.servicehi.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author guoguangxiao
 * @date 2019/5/5 15:59:31
 */
public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String DATE_FORMAT_DAY = "yyyy-MM-dd";
    private static final String LONG_FORMAT_DAY = "yyyyMMdd";
    private static final String LONG_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final String[] zodiacArr = new String[]{"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final String[] constellationArr = new String[]{"水瓶座", "双鱼座", "牡羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
    private static final int[] constellationEdgeDay = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
    private static final String[] weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final Integer[] weekDayNum = new Integer[]{7, 1, 2, 3, 4, 5, 6};
    private static final long MINUTE = 60000L;
    private static final long HOUR = 3600000L;
    private static final long DAY = 86400000L;
    private static final long MONTH = 2678400000L;
    private static final long YEAR = 32140800000L;
    private static final String[] WEEKS = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final DateFormat haveSeparatSdf = new SimpleDateFormat(DATE_FORMAT_DAY);
    private static final DateFormat noSeparatSdf = new SimpleDateFormat(LONG_FORMAT_DAY);

    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.compareTo(getDatePlusDays(new Date(), -7)) > 0 ? parseTextDesc(date) : formatDate(date, "yyyy-MM-dd");
        }
    }

    public static String getOldTimeFormatText(Date date) {
        return date == null ? null : parseTextDesc(date);
    }

    public static String getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static Integer getWeekOfDateNum(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 1;
        }

        return weekDayNum[w];
    }

    public static Date previous(int days) {
        return new Date(System.currentTimeMillis() - (long)days * 3600000L * 24L);
    }

    public static String formatDateLong(Date d) {
        return d == null ? "" : (new SimpleDateFormat("yyyyMMddHHmmss")).format(d);
    }

    public static String formatDateTime(Date d) {
        return d == null ? "" : (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(d);
    }

    public static String formatDateTime(long d) {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(d);
    }

    public static String formatDateTime(String format, Date date) {
        return (new SimpleDateFormat(format)).format(date);
    }

    public static String formatDate(Date d) {
        return d == null ? "" : (new SimpleDateFormat("yyyy/MM/dd")).format(d);
    }

    public static Date formatDay(Date d) {
        if (d == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                return format.parse(format.format(d));
            } catch (ParseException var3) {
                logger.error("formatDay error,the error is ={}", var3);
                return null;
            }
        }
    }

    public static Date parseDate(String d) {
        try {
            return (new SimpleDateFormat("yyyy/MM/dd")).parse(d);
        } catch (Exception var2) {
            logger.error("parseDate error,the error is ={}", var2);
            return null;
        }
    }

    public static Date parseDateyyyyMMdd(String d) {
        try {
            return (new SimpleDateFormat("yyyyMMdd")).parse(d);
        } catch (Exception var2) {
            logger.error("parseDateyyyyMMdd error", var2);
            return null;
        }
    }

    public static Date parseyyyyMMddDate(String d) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd")).parse(d);
        } catch (Exception var2) {
            logger.error("parseyyyyMMddDate error,the error is ={}", var2);
            return null;
        }
    }

    public static Date parseyyyyMMddHHmmssDate(String d) {
        try {
            return (new SimpleDateFormat("yyyyMMddHHmmss")).parse(d);
        } catch (Exception var2) {
            logger.error("parseyyyyMMddHHmmssDate error,the error is ={}", var2);
            return null;
        }
    }

    public static Date parseDateTime(String dt) {
        try {
            return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dt);
        } catch (Exception var2) {
            logger.error("parseDateTime error,the error is ={}", var2);
            return null;
        }
    }

    public static int getYearOfDate(Date speciDate) {
        if (null != speciDate) {
            Calendar c = Calendar.getInstance();
            c.setTime(speciDate);
            return c.get(1);
        } else {
            return -1;
        }
    }

    public static int getMonthOfDate(Date date) {
        if (null != date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(2) + 1;
        } else {
            return -1;
        }
    }

    public static int getDayOfDate(Date date) {
        if (null != date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(5);
        } else {
            return -1;
        }
    }

    public static int getHourOfDate(Date date) {
        if (null != date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(11);
        } else {
            return -1;
        }
    }

    public static int getMinuteOfDate(Date date) {
        if (null != date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(12);
        } else {
            return -1;
        }
    }

    public static int getSecondOfDate(Date date) {
        if (null != date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.get(13);
        } else {
            return -1;
        }
    }

    public static long getMillisOfDate(Date date) {
        if (null != date) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return c.getTimeInMillis();
        } else {
            return -1L;
        }
    }

    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        } else {
            int yearNow = cal.get(1);
            int monthNow = cal.get(2);
            int dayOfMonthNow = cal.get(5);
            cal.setTime(birthDay);
            int yearBirth = cal.get(1);
            int monthBirth = cal.get(2);
            int dayOfMonthBirth = cal.get(5);
            int age = yearNow - yearBirth;
            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (dayOfMonthNow < dayOfMonthBirth) {
                        --age;
                    }
                } else {
                    --age;
                }
            }

            return age;
        }
    }

    public static String formatDayHour(long ms, int validDay) {
        int temp = (int)(ms / 3600000L);
        int day;
        int hour;
        if (temp % 24 == 0) {
            day = (validDay * 24 - temp) / 24;
            hour = 0;
        } else {
            day = (validDay * 24 - temp) / 24;
            if (temp < 24) {
                hour = 24 - temp;
            } else {
                hour = 24 - temp % 24;
            }
        }

        return day + "天" + hour + "小时";
    }

    public static String formatDayHourMinuteSeconds(long ms) {
        int seconds = (int)(ms / 1000L) % 60;
        int minute = (int)(ms / 1000L / 60L) % 60;
        int hour = (int)(ms / 1000L / 60L / 60L) % 60;
        int day = (int)(ms / 1000L / 60L / 60L / 24L) % 24;
        StringBuilder stringBuffer = new StringBuilder();
        if (day > 0) {
            stringBuffer.append(day).append("天");
        }

        if (hour > 0) {
            stringBuffer.append(hour).append("小时");
        }

        if (minute > 0) {
            stringBuffer.append(minute).append("分");
        }

        if (seconds > 0) {
            stringBuffer.append(seconds).append("秒");
        }

        return stringBuffer.toString();
    }

    public static String date2Zodica(Calendar time) {
        return zodiacArr[time.get(1) % 12];
    }

    public static String date2Constellation(Calendar time) {
        int month = time.get(2);
        int day = time.get(5);
        if (day < constellationEdgeDay[month]) {
            --month;
        }

        return month >= 0 ? constellationArr[month] : constellationArr[11];
    }

    public static final Timestamp nowDateTime() {
        return new Timestamp((new Date()).getTime());
    }

    public static final Date nowDate() {
        return new Date();
    }

    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newSmdate = null;
        Date newBdate = null;

        try {
            newSmdate = sdf.parse(sdf.format(smdate));
            newBdate = sdf.parse(sdf.format(bdate));
        } catch (Exception var12) {
            logger.error(var12.getMessage(), var12);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(newSmdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(newBdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static int hourBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newSmdate = null;
        Date newBdate = null;

        try {
            newSmdate = sdf.parse(sdf.format(smdate));
            newBdate = sdf.parse(sdf.format(bdate));
        } catch (Exception var12) {
            logger.error(var12.getMessage(), var12);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(newSmdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(newBdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / 3600000L;
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static int daysBetweenStr(String smdate, String bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newSmdate = null;
        Date newBdate = null;

        try {
            newSmdate = sdf.parse(smdate);
            newBdate = sdf.parse(bdate);
        } catch (Exception var6) {
            logger.error(var6.getMessage(), var6);
        }

        return daysBetween(newSmdate, newBdate);
    }

    public static Boolean isOverdate(Date inputDate) {
        Date nowDate = new Date();
        return nowDate.before(inputDate);
    }

    public static Date getDatePlusMonths(Date inputDate, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(2, monthNum);
        return calendar.getTime();
    }

    public static Date getDatePlusDays(Date inputDate, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(7, dayNum);
        return calendar.getTime();
    }

    public static Date getDatePlusHour(Date inputDate, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(11, dayNum);
        return calendar.getTime();
    }

    public static int getBetweenMonth(Date date1, Date date2) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int yearBetween = cal2.get(1) - cal1.get(1);
        int monthBetween = cal2.get(2) - cal1.get(2);
        int d1 = Integer.parseInt(sdf2.format(cal1.getTime()));
        int d2 = Integer.parseInt(sdf2.format(cal2.getTime()));
        int monthMax = cal2.getActualMaximum(5);
        if (d1 > monthMax) {
            d1 = monthMax;
        }

        if (d1 > d2) {
            --monthBetween;
        }

        return 12 * yearBetween + monthBetween;
    }

    public static int betweenMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int yearBetween = cal2.get(1) - cal1.get(1);
        int monthBetween = cal2.get(2) - cal1.get(2);
        return yearBetween == 0 && monthBetween == 0 ? 0 : -1;
    }

    public static Timestamp getSysTimestamp() {
        TimeZone zone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(zone);
        return new Timestamp((new Date()).getTime());
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.format(date);
        }
    }

    public static Date formatDate(long time) {
        return new Date(time);
    }

    public static String formateDateStr(Long time) {
        return formatDate(formatDate(time), "yyyy-MM-dd HH:mm:ss");
    }

    public static String timeStampUrl(String url) {
        return url != null ? url + "?t=" + System.currentTimeMillis() : "";
    }

    public static Date changeDate(Integer dayNum, String op) {
        if (dayNum == null) {
            return null;
        } else {
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = new Date();
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(5, date.get(5) + ("+".equals(op) ? dayNum : -dayNum));
            Date endDate = null;

            try {
                endDate = dft.parse(dft.format(date.getTime()));
            } catch (ParseException var7) {
                logger.error("changeDate error,the error is ={}", var7);
            }

            return endDate;
        }
    }

    public static Date changeDateByHour(Date date, Integer hourNum, String op) {
        if (date == null) {
            return null;
        } else if (hourNum != null && hourNum != 0) {
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(10, cal.get(10) + ("+".equals(op) ? hourNum : -hourNum));
            Date after = null;

            try {
                after = dft.parse(dft.format(cal.getTime()));
            } catch (Exception var7) {
                logger.error("changeDateByHour error,the error is ={}", var7);
            }

            return after;
        } else {
            return date;
        }
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException var4) {
            logger.error("isValidDate error,the error is ={}", var4);
            convertSuccess = false;
        }

        return convertSuccess;
    }

    public static String dateOfWeek(int index, String format) {
        Calendar calendar = Calendar.getInstance();

        while(calendar.get(7) != 2) {
            calendar.add(5, -1);
        }

        calendar.add(5, index - 1);
        return (new SimpleDateFormat(format)).format(calendar.getTime());
    }

    public static String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(5, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String[] mondays = df.format(monday).split("-");
        String month = mondays[1].length() == 1 ? "0" + mondays[1] : mondays[1];
        String day = mondays[2].length() == 1 ? "0" + mondays[2] : mondays[2];
        return mondays[0] + "-" + month + "-" + day;
    }

    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(5, mondayPlus + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String[] mondays = df.format(monday).split("-");
        String month = mondays[1].length() == 1 ? "0" + mondays[1] : mondays[1];
        String day = mondays[2].length() == 1 ? "0" + mondays[2] : mondays[2];
        return mondays[0] + "-" + month + "-" + day;
    }

    public static String getMinMonthDate() {
        Calendar calendar = Calendar.getInstance();

        try {
            Date theDate = calendar.getTime();
            GregorianCalendar gcLast = (GregorianCalendar)Calendar.getInstance();
            gcLast.setTime(theDate);
            gcLast.set(5, 1);
            return ((SimpleDateFormat)dateFormat.get()).format(gcLast.getTime());
        } catch (Exception var3) {
            logger.error("getMinMonthDate error,the error is ={}", var3);
            return null;
        }
    }

    public static String getMaxMonthDate() {
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.set(5, calendar.getActualMaximum(5));
            return ((SimpleDateFormat)dateFormat.get()).format(calendar.getTime());
        } catch (Exception var2) {
            logger.error("getMaxMonthDate error,the error is ={}", var2);
            return null;
        }
    }

    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7);
        return dayOfWeek == 1 ? -6 : 2 - dayOfWeek;
    }

    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        int lastDay = cal.getActualMaximum(5);
        cal.set(5, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static Map getMondayCount(String from, String to, int day) {
        Map<Integer, String> map = new HashMap();
        Calendar calendar = Calendar.getInstance();
        String[] array = new String[]{from, to};
        Date[] ds = new Date[array.length];

        int count;
        for(count = 0; count < array.length; ++count) {
            String[] fs = array[count].split("[^\\d]+");
            calendar.set(Integer.parseInt(fs[0]), Integer.parseInt(fs[1]) - 1, Integer.parseInt(fs[2]));
            ds[count] = calendar.getTime();
        }

        count = 0;
        Date x = ds[0];

        while(x != null && x.compareTo(ds[1]) <= 0) {
            calendar.setTime(x);
            calendar.add(5, 1);
            Date xx = calendar.getTime();
            if (calendar.get(7) == day) {
                String date = formatDate(xx, "yyyy-MM-dd");
                ++count;
                map.put(count, date);
            }
        }

        return map;
    }

    public static Date getMouthDate(int type) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Calendar c = Calendar.getInstance();
            c.add(2, 0);
            c.set(5, 1);
            Calendar ca = Calendar.getInstance();
            ca.set(5, ca.getActualMaximum(5));
            if (type == 0) {
                return df.parse(format.format(c.getTime()));
            } else {
                return type == 1 ? df.parse(format.format(ca.getTime())) : df.parse(format.format(new Date()));
            }
        } catch (ParseException var5) {
            logger.error("getMouthDate error,the error is ={}", var5);
            return null;
        }
    }

    public static String getWeeks(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return WEEKS[w];
    }

    public static Date getWeekDate(int day1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String newDate = sdf.format(new Date());

        try {
            Date time = sdf.parse(newDate);
            cal.setTime(time);
            int dayWeek = cal.get(7);
            if (1 == dayWeek) {
                cal.add(5, -1);
            }

            cal.setFirstDayOfWeek(2);
            cal.add(5, cal.getFirstDayOfWeek() - cal.get(7));
            cal.add(5, day1);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(sdf.format(cal.getTime()));
        } catch (ParseException var7) {
            logger.error("getWeekDate error,the error is ={}", var7);
            return null;
        }
    }

    public static String convertWeekByDate(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int dayWeek = cal.get(7);
        if (1 == dayWeek) {
            cal.add(5, -1);
        }

        cal.setFirstDayOfWeek(2);
        int day = cal.get(7);
        cal.add(5, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(5, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        return imptimeBegin + ":" + imptimeEnd;
    }

    public static Date getMondayOfThisWeek() {
        Long millons = System.currentTimeMillis() / 86400000L * 86400000L - (long)TimeZone.getDefault().getRawOffset();
        Date date = new Date(millons);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, -1);
        cal.set(7, 2);
        return cal.getTime();
    }

    public static Date getNextMonday() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(7);
        if (week > 2) {
            cal.add(5, -(week - 2) + 7);
        } else {
            cal.add(5, 2 - week + 7);
        }

        long millions = cal.getTime().getTime() / 86400000L * 86400000L - (long)TimeZone.getDefault().getRawOffset();
        return new Date(millions);
    }

    public static Date getNextSunday() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(7);
        if (week > 1) {
            cal.add(5, -(week - 1) + 7);
        } else {
            cal.add(5, 1 - week + 7);
        }

        long millions = cal.getTime().getTime() / 86400000L * 86400000L + 86400000L - (long)TimeZone.getDefault().getRawOffset();
        return new Date(millions);
    }

    public static Date getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(7) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        c.add(5, -dayOfWeek + 7);
        Long millons = c.getTimeInMillis() / 86400000L * 86400000L - (long)TimeZone.getDefault().getRawOffset() + 86400000L;
        return new Date(millons);
    }

    public static Date getFirstDayOfThisMonth() {
        Calendar c = Calendar.getInstance();
        c.add(2, 0);
        c.set(5, 1);
        Long millons = c.getTimeInMillis() / 86400000L * 86400000L - (long)TimeZone.getDefault().getRawOffset();
        return new Date(millons);
    }

    public static Date getLastDayOfThisMonth() {
        Calendar c = Calendar.getInstance();
        c.set(5, c.getActualMaximum(5));
        Long millons = c.getTimeInMillis() / 86400000L * 86400000L - (long)TimeZone.getDefault().getRawOffset() + 86400000L;
        return new Date(millons);
    }

    public static Date getDatePlusMinute(Date inputDate, int minuteNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        calendar.add(12, minuteNum);
        return calendar.getTime();
    }

    public static int compareDate(Date dt1, Date dt2) {
        try {
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else {
                return dt1.getTime() < dt2.getTime() ? -1 : 0;
            }
        } catch (Exception var3) {
            logger.error("compareDate error,", var3);
            return 0;
        }
    }

    public static String getBeforeTimeFormatText(Date date) {
        if (date == null) {
            return null;
        } else {
            long diff = System.currentTimeMillis() - date.getTime();
            long r = 0L;
            if (diff / 60000L > 30L) {
                return "30分钟前";
            } else if (diff > 60000L) {
                r = diff / 60000L;
                return r + "分钟前";
            } else {
                return "刚刚";
            }
        }
    }

    public static Boolean isAfterForDay(Date date1, Date date2) {
        if (date1 != null && date2 != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                return format.parse(format.format(date1)).after(format.parse(format.format(date2)));
            } catch (ParseException var4) {
                logger.error("isAfterForDay error,the error is ={}", var4);
                return null;
            }
        } else {
            return false;
        }
    }

    public static Date changeDateTime(Integer dayNum, String op) {
        if (dayNum == null) {
            return null;
        } else {
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date beginDate = new Date();
            Calendar date = Calendar.getInstance();
            date.setTime(beginDate);
            date.set(5, date.get(5) + ("+".equals(op) ? dayNum : -dayNum));
            Date endDate = null;

            try {
                endDate = dft.parse(dft.format(date.getTime()));
            } catch (ParseException var7) {
                logger.error("changeDateTime error,the error is ={}", var7);
            }

            return endDate;
        }
    }

    public static Date getDateSubMinute(Date inputDate, int minuteNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int now = calendar.get(12);
        calendar.set(12, now - minuteNum);
        return calendar.getTime();
    }

    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        Date date = calendar.getTime();
        return date.getTime();
    }

    public static Long endOfTodDayForSecond() {
        long endOfTodDay = endOfTodDay();
        return (endOfTodDay - (new Date()).getTime()) / 1000L;
    }

    public static Long endOfHourForSecond() {
        Calendar c = Calendar.getInstance();
        c.add(10, 1);
        c.set(12, 0);
        c.set(13, 0);
        return (c.getTime().getTime() - System.currentTimeMillis()) / 1000L;
    }

    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long day = 0L;
        long hour = 0L;
        long min = 0L;
        long sec = 0L;

        try {
            Date one = df.parse(str1);
            Date two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }

            day = diff / 86400000L;
            hour = diff / 3600000L - day * 24L;
            min = diff / 60000L - day * 24L * 60L - hour * 60L;
            sec = diff / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
        } catch (ParseException var19) {
            logger.error("getDistanceTimes error,the error is ={}", var19);
        }

        return new long[]{day, hour, min, sec};
    }

    public static String getGreeting() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int hour1 = cal.get(11);
        if (hour1 >= 4 && hour1 < 8) {
            return "早安";
        } else if (hour1 >= 8 && hour1 < 11) {
            return "上午好";
        } else if (hour1 >= 11 && hour1 < 13) {
            return "中午好";
        } else if (hour1 >= 13 && hour1 < 18) {
            return "下午好";
        } else if (hour1 >= 18 && hour1 < 22) {
            return "晚上好";
        } else {
            return hour1 < 22 && hour1 >= 4 ? "你好" : "晚安";
        }
    }

    public static String parseTextDesc(Date date) {
        long diff = System.currentTimeMillis() - date.getTime();
        long r = 0L;
        if (diff > 32140800000L) {
            r = diff / 32140800000L;
            return r + "年前";
        } else if (diff > 2678400000L) {
            r = diff / 2678400000L;
            return r + "个月前";
        } else if (diff > 86400000L) {
            r = diff / 86400000L;
            return r + "天前";
        } else if (diff > 3600000L) {
            r = diff / 3600000L;
            return r + "个小时前";
        } else if (diff > 60000L) {
            r = diff / 60000L;
            return r + "分钟前";
        } else {
            return "刚刚";
        }
    }

    public static boolean compareDateStr(String beforeDateStr,String afterDateStr) throws ParseException {
        Long beforeDate = 0L;
        Long afterDate  = 0L;

        if(StringUtils.isNotBlank(beforeDateStr)){
            if(beforeDateStr.indexOf("-")<0){
                beforeDate = noSeparatSdf.parse(beforeDateStr.substring(0,8)).getTime();
            }else{
                beforeDate = haveSeparatSdf.parse(beforeDateStr).getTime();
            }
        }

        if(StringUtils.isNotBlank(afterDateStr)){
            if(afterDateStr.indexOf("-")<0){
                afterDate = noSeparatSdf.parse(afterDateStr.substring(0,8)).getTime();
            }else{
                afterDate = haveSeparatSdf.parse(afterDateStr).getTime();
            }
        }

        if(beforeDate < afterDate){
            return true ;
        }else{
            return false ;
        }
    }

    public static String separatDateStr(String dateStr){
        String returnStr = "";
        if(StringUtils.isNotBlank(dateStr)){
            if(dateStr.indexOf("-")<0){
                returnStr = dateStr.substring(4,6) + "-" + dateStr.substring(6,8);
                return returnStr;
            }else{
                returnStr = dateStr.substring(dateStr.indexOf("-") + 1);
                return returnStr;
            }
        }else{
            return returnStr;
        }
    }

    public static void main(String[] args) throws ParseException {
//        separatDateStr("1987-09-06");
//        separatDateStr("19730118");

        compareDateStr("1998071500","2019-02-01");
    }

    private DateUtil() {
    }

}
