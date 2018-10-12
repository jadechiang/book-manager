package wuxicloud.ealen.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EalenXie on 2017-12-11 18:21
 */
public class TestTime {

    public static void main(String[] args) {
        Long time = System.currentTimeMillis();
        Date date= new Date();
//        System.out.println(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = simpleDateFormat.format(date);
        System.out.println(dateTime);

    }

}
