package ealen.bookmanager.util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
/**
 * #Created with IntelliJ IDEA
 * #Author : EalenXie
 * #CreateTime : 2017-05-04 09:37
 */
@Component("sender")
public class SendEmailUtil {
    @Resource
    private JavaMailSender mailSender;                                          //邮件发送
    @Value("${spring.mail.username}")
    private String emailFrom;
    @Value("${send.mail.to}")
    private String emailTo;
    /**
     * 传入主题,内容,附件进行邮件发送
     */
    public void sendMessage(String title, String content, MultipartFile file) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(title);
            helper.setText(content);
            if (!file.isEmpty()) {
                helper.addAttachment(file.getOriginalFilename(), file);
            }
            helper.setFrom(emailFrom);
            helper.setTo(emailTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
