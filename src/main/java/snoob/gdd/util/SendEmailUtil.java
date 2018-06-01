package snoob.gdd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.exception.GddException;
import snoob.gdd.exception.ExceptionHandle;
import snoob.gdd.model.EmailCode;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.MessageFormat;
import java.util.Date;

/**
 * 发送验证码到指定邮箱
 */
@Component
public class SendEmailUtil {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 文本格式
     * @param emailCode
     * @return true表示发送成功,false表示发送失败
     */
    public void sendSimpleEmail(EmailCode emailCode){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailCode.getSender());
        simpleMailMessage.setTo(emailCode.getReceiver());
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setSubject(emailCode.getSubject());
        simpleMailMessage.setText(emailCode.getContent());
        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            logger.debug(MessageFormat.format("【发送邮件错误】--{0}",e.getMessage()));
            throw new GddException(ResultEnum.ERROR_EMAIL_SENDER);
        }
    }

    /**
     * html格式
     * @param emailCode
     * @return
     */
    public Object sendHtmlEmail(EmailCode emailCode) {
        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //true表示需要创建一个multipart message
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(emailCode.getSender());
            mimeMessageHelper.setTo(emailCode.getReceiver());
            mimeMessageHelper.setSentDate(new Date());
            mimeMessageHelper.setSubject(emailCode.getSubject());
            //MessageFormat.format("验证码: {0}",RandomStrUtil.getCodeStr())
            mimeMessageHelper.setText(emailCode.getContent(), true);
            mailSender.send(mimeMessage);
            return 1;
        }catch (MessagingException e){
            return 0;
        }
    }
}
