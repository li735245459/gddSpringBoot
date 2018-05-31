package snoob.gdd.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.service.EmailService;
import snoob.gdd.service.UserService;

import javax.annotation.Resource;


/**
 * 用户模块
 */
@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserController {

    @Value("${spring.mail.username}")
    private String sender;

    @Resource
    private UserService userService;
    @Resource
    private EmailService emailService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Object register(@RequestBody User user){
        return userService.register(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        return userService.login(user);
    }

    /**
     * 通过邮箱找回密码
     * @param receiver
     * @param type
     * @return
     */
//    @GetMapping("/sendEmail/{type}/{receiver}")
//    public Object sendEmail(@PathVariable(value = "type") String type, @PathVariable(value = "receiver") String receiver){
//        return emailService.sendSimpleEmail(type,sender,receiver,"通过邮箱找回密码功能",
//                MessageFormat.format("验证码: {0}",RandomStringUtil.getCodeStr()));
////        String content=MessageFormat.format("<html><body><h3><a href='https://www.baidu.com/'>验证码</a>:{0}</h3></body></html>",RandomStringUtil.getCodeStr());
////        return emailService.sendHtmlEmail(type,sender,receiver,"通过邮箱找回密码功能",content);
//    }
}
