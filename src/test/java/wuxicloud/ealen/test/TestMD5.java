package wuxicloud.ealen.test;


import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * #Author : EalenXie
 * #CreateTime : 2017-04-18 14:36
 */
public class TestMD5 {


    @Test
    public void test() {
        String pass = "123456";
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder(4);
        String hashPass = encode.encode(pass);
        System.out.println(hashPass);
    }
}
