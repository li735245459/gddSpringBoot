package snoob.gdd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.exception.GddException;
import snoob.gdd.mapper.EmailCodeMapper;
import snoob.gdd.model.EmailCode;
import snoob.gdd.service.EmailService;
import snoob.gdd.util.RandomStrUtil;
import snoob.gdd.util.ResultUtil;
import snoob.gdd.util.SendEmailUtil;

import javax.annotation.Resource;
import java.text.MessageFormat;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private SendEmailUtil sendEmail;

    @Resource
    private EmailCodeMapper emailCodeDao;

    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 发送邮件: 文本格式
     *
     * @param type
     * @param receiver
     * @return
     * @throws Exception
     */
    @Override
    public Object sendEmail(String type, String receiver) throws Exception {
        EmailCode emailCode = new EmailCode();
        emailCode.setId(RandomStrUtil.getUuidStr());
        emailCode.setType(type);
        emailCode.setSender(sender);
        emailCode.setReceiver(receiver);
        if ("1".equals(type)) { // 忘记密码模块发送验证码功能
            emailCode.setSubject("忘记密码模块发送验证码功能");
            String code = RandomStrUtil.getCodeStr();
            emailCode.setCode(code);
            emailCode.setContent(MessageFormat.format("验证码:{0}", code));
        }
        // 发送邮件
        sendEmail.sendSimpleEmail(emailCode);
        // 添加邮件内容到数据库
        emailCodeDao.insertSelective(emailCode);
        return ResultUtil.success();
    }

    /**
     * 发送邮件: html格式
     *
     * @param type
     * @param receiver
     * @return
     * @throws Exception
     */
    @Override
    public Object sendHtmlEmail(String type, String receiver) throws Exception {
        return null;
    }

    /**
     * 校验邮箱和验证码
     *
     * @param type
     * @param email
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public Object checkEmailCode(String type, String email, String code) throws Exception {
        EmailCode emailCode = new EmailCode();
        emailCode.setType(type);
        emailCode.setReceiver(email);
        emailCode.setCode(code);
        if (emailCodeDao.select(emailCode).isEmpty()) {
            throw new GddException(ResultEnum.ERROR_EMAIL_CODE);
        } else {
            return ResultUtil.success();
        }
    }
}
