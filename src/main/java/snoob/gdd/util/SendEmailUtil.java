package snoob.gdd.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import snoob.gdd.model.EmailCode;

import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * 发送验证码到指定邮箱
 */
@Component
public class SendEmailUtil {

    @Autowired
    private JavaMailSender mailSender;


    /**
     * 文本格式
     *
     * @param emailCode
     * @return true表示发送成功, false表示发送失败
     */
    public void sendSimpleEmail(EmailCode emailCode) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailCode.getSender());
        simpleMailMessage.setTo(emailCode.getReceiver());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setSubject(emailCode.getSubject());
        simpleMailMessage.setText(emailCode.getContent());
        mailSender.send(simpleMailMessage);
    }

    /**
     * html格式
     *
     * @param emailCode
     * @return
     */
    public void sendHtmlEmail(EmailCode emailCode) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true); //true表示需要创建一个multipart message
        mimeMessageHelper.setFrom(emailCode.getSender());
        mimeMessageHelper.setTo(emailCode.getReceiver());
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setSubject(emailCode.getSubject());
        mimeMessageHelper.setText(emailCode.getContent(), true);
        mailSender.send(mimeMessage);
    }
}
