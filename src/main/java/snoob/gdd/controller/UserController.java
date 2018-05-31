package snoob.gdd.controller;

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
    @GetMapping("/sendEmail/{receiver}/{type}")
    public Object sendEmail(@PathVariable(value = "receiver") String receiver, @PathVariable(value = "type") String type){
//        return emailService.sendSimpleEmail(receiver, type);
        return emailService.sendHtmlEmail(receiver, type);
    }
}
