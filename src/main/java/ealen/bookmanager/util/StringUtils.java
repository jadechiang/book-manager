package ealen.bookmanager.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class StringUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //是否为空
    public static boolean isEmpty(String str) {
        return (str == null) || (str.length() == 0);
    }

    //是否不为空
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    //去空格
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    //得到当前日期
    public static String getCurrentDateString() {
        Date date = new Date();
        return sdf.format(date);
    }
}
