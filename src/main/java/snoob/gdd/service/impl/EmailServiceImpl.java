package snoob.gdd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import snoob.gdd.GlobalCustomException;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.EmailCodeMapper;
import snoob.gdd.model.EmailCode;
import snoob.gdd.service.EmailService;
import snoob.gdd.util.OnlyUtil;
import snoob.gdd.util.StrUtil;
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

    @Resource
    private OnlyUtil onlyUtil;

    /**
     * 发送邮件: 文本格式
     *
     * @param type
     * @param receiver
     * @return
     * @throws Exception
     */
    @Override
    public Object sendEmail(String type, String receiver) {
        EmailCode emailCode = new EmailCode();
        emailCode.setId(StrUtil.getUuidStr());
        emailCode.setType(type);
        emailCode.setSender(sender);
        emailCode.setReceiver(receiver);
        /**
         * 忘记密码模块发送验证码功能
         */
        if ("1".equals(type)) {
            emailCode.setSubject("忘记密码模块发送验证码功能");
            String code = StrUtil.getCodeStr();
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
        EmailCode emailCode = new EmailCode();
        emailCode.setType(type);
        emailCode.setSender(sender);
        emailCode.setReceiver(receiver);
        // 检查邮箱的有效性
        if (!onlyUtil.emailUsed(receiver)) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_ILLEGAL);
        }
        /**
         * 忘记密码模块
         */
        if ("0".equals(type)) {
            emailCode.setSubject("忘记密码模块验证码功能：");
            String code = StrUtil.getCodeStr();
            emailCode.setCode(code);
            String content = "<h1>验证码:</h1><a href='http:www.baidu.com' style='color:#F00'>{0}</a>";
            emailCode.setContent(content);
        } else {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_CODE_MODULE_ILLEGAL);
        }
        // 添加邮件内容到数据库
        emailCodeDao.insertSelective(emailCode);
        // 发送邮件
        sendEmail.sendHtmlEmail(emailCode);
        return ResultUtil.success();
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
    public Object checkEmailCode(String type, String email, String code) {
        EmailCode emailCode = new EmailCode();
        emailCode.setType(type);
        emailCode.setReceiver(email);
        emailCode.setCode(code);
        if (emailCodeDao.select(emailCode).isEmpty()) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_OR_CODE);
        } else {
            return ResultUtil.success();
        }
    }

}
