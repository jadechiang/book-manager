package wuxicloud.ealen.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-07-18 12:25
 */
public class TestFormat {
    public static void main(String[] args) throws ParseException {
//        Thu Jul 06 00:00:00 CST 2017

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        String date=format.format(Date.parse("Thu Jul 06 00:00:00 CST 2017"));
        System.out.println(date);
    }
}
