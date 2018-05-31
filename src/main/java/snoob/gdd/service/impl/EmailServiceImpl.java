package snoob.gdd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import snoob.gdd.service.EmailService;
import snoob.gdd.util.Message;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private Message message;

    @Autowired
    private JavaMailSender mailSender;

    private SimpleMailMessage simpleMailMessage;

    private MimeMessageHelper mimeMessageHelper;

    /**
     * 简单格式
     * @param type
     * @param receiver
     * @param subject
     * @param content
     * @return
     */
    @Override
    public Object sendSimpleEmail(String type, String sender, String receiver, String subject, String content) {
        simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(receiver);
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        try {
            mailSender.send(simpleMailMessage);
            // 保存到数据库
            message.setCode(1);
            message.setMessage("验证码已发送至您的邮箱");
        } catch (Exception e) {
            message.setCode(0);
            message.setMessage("验证码发送失败");
        }
        return message;
    }

    /**
     * html格式
     * @param type
     * @param receiver
     * @param subject
     * @param content
     * @return
     */
    @Override
    public Object sendHtmlEmail(String type, String sender, String receiver, String subject, String content) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //true表示需要创建一个multipart message
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.setSubject("忘记密码");
//            String content=MessageFormat.format("<html><body><h3>验证码:{0}</h3></body></html>",RandomString.getCodeStr());
            mimeMessageHelper.setText(content, true);
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
