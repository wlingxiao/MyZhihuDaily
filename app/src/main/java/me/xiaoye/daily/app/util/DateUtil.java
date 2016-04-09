package me.xiaoye.daily.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by VAIO on 2016/4/8.
 */
public class DateUtil {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
    private static int i = -1;
    public static String nextDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, i--);
        return dateFormat.format(calendar.getTime());
    }
}
