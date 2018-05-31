package snoob.gdd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snoob.gdd.mapper.EmailCodeMapper;
import snoob.gdd.model.EmailCode;
import snoob.gdd.service.EmailService;
import snoob.gdd.util.SendEmailUtil;

import javax.annotation.Resource;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private SendEmailUtil sendEmail;
    @Resource
    private EmailCodeMapper emailCodeDao;

    /**
     * 忘记密码模块同邮箱获取验证码
     * @param emailCode
     * @return
     */
    @Override
    public Object forgetPassword(EmailCode emailCode) {
        if(sendEmail.sendSimpleEmail(emailCode)){
            // 发送成, 添加邮件内容到数据库
            if(emailCodeDao.insertSelective(emailCode) > 0){
                // 添加成功
            }else{
                // 添加失败
            }
        }else{
            // 发送失败
        }
        return null;
   }
}
