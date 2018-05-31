package snoob.gdd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import snoob.gdd.service.EmailService;
import snoob.gdd.util.Message;
import snoob.gdd.util.RandomString;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private Message message;

    @Override
    public Object sendSimpleEmail(String receiver, String type) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(receiver);
        mailMessage.setSentDate(new Date());
        mailMessage.setSubject("忘记密码");
        mailMessage.setText("您的验证码为:"+RandomString.getCodeStr());
        try {
            mailSender.send(mailMessage);
            message.setCode(1);
            message.setMessage("验证码已发送至您的邮箱");
        } catch (Exception e) {
            message.setCode(0);
            message.setMessage("验证码发送失败");
        }
        return message;
    }

    @Override
    public Object sendHtmlEmail(String receiver, String type) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(sender);
            helper.setTo(receiver);
            helper.setSentDate(new Date());
            helper.setSubject("忘记密码");
            String content=MessageFormat.format("<html><body><h3>验证码:{0}</h3></body></html>",RandomString.getCodeStr());
            helper.setText(content, true);
            mailSender.send(mimeMessage);
            message.setCode(1);
            message.setMessage("验证码已发送至您的邮箱");
        }catch (MessagingException e){
            message.setCode(0);
            message.setMessage("验证码发送失败");
        }finally {
            return message;
        }
    }

}
