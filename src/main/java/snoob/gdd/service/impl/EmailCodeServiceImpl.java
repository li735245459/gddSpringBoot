package snoob.gdd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.EmailCodeMapper;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.EmailCode;
import snoob.gdd.model.User;
import snoob.gdd.service.EmailCodeService;
import snoob.gdd.service.UserService;
import snoob.gdd.util.StrUtil;
import snoob.gdd.util.ResultUtil;
import snoob.gdd.util.SendEmailUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailCodeServiceImpl implements EmailCodeService {

    @Autowired
    private SendEmailUtil sendEmail;

    @Resource
    private EmailCodeMapper emailCodeMapper;

    @Value("${spring.mail.username}")
    private String sender;

    @Resource
    private UserService userService;


    /**
     * 发送html格式邮件
     *
     * @param type
     * @param receiver
     * @return
     * @throws Exception
     */
    @Override
    public Object sendHtmlEmail(String type, String receiver) throws Exception {
        EmailCode item = new EmailCode();
        item.setCodeType(type);
        item.setSender(sender);
        item.setReceiver(receiver);
        // 检查邮箱是否存在
        if (!userService.checkEmail(receiver)) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_ILLEGAL);
        }
        /*忘记密码模块*/
        if ("0".equals(type)) {
            item.setSubject("忘记密码模块验证码功能：");
            String code = StrUtil.getCodeStr();
            item.setCode(code);
            String content = MessageFormat.format("<h1>验证码:</h1><a href='http:www.baidu.com' style='color:#F00'>{0}</a>", code);
            item.setContent(content);
        } else {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_CODE_MODULE_ILLEGAL);
        }
        // 添加邮件内容到数据库
        emailCodeMapper.insertSelective(item);
        // 发送邮件
        sendEmail.sendHtmlEmail(item);
        return ResultUtil.success();
    }

//    EmailCode emailCode = new EmailCode();
//        emailCode.setId(StrUtil.getUuidStr());
//        emailCode.setCodeType(type);
//        emailCode.setSender(sender);
//        emailCode.setReceiver(receiver);
//    /*忘记密码模块发送验证码功能*/
//        if ("1".equals(type)) {
//        emailCode.setSubject("忘记密码模块发送验证码功能");
//        String code = StrUtil.getCodeStr();
//        emailCode.setCode(code);
//        emailCode.setContent(MessageFormat.format("验证码:{0}", code));
//    }
//    // 发送邮件
//        sendEmail.sendSimpleEmail(emailCode);
//    // 添加邮件内容到数据库
//        emailCodeMapper.insertSelective(emailCode);
//        return ResultUtil.success();

    /**
     * 检查邮箱验证码是否匹配
     *
     * @param type
     * @param email
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public Object checkEmailCode(String type, String email, String code) throws Exception {
        EmailCode item = new EmailCode();
        item.setCodeType(type);
        item.setReceiver(email);
        item.setCode(code);
        if (emailCodeMapper.selectOne(item) == null) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_OR_CODE);
        }
        return ResultUtil.success();
    }

    /**
     * 分页查询邮件
     *
     * @param emailCode
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object page(EmailCode emailCode, Integer pageNumber, Integer pageSize) throws Exception {
        Example example = new Example(User.class);
        example.orderBy("createTime").asc();
        // 开启分页模式
        PageHelper.startPage(pageNumber, pageSize);
        List<EmailCode> emailCodes = emailCodeMapper.selectByExample(example);
        // 获取分页对象
        PageInfo page = new PageInfo(emailCodes);
        return ResultUtil.success(page);
    }

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除邮件
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        if ("all".equals(id)) {
            emailCodeMapper.delete(new EmailCode());
        } else {
            List<String> ids = Arrays.asList(id.split(","));
            emailCodeMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }
}
