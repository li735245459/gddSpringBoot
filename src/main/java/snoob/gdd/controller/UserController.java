package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.service.EmailService;
import snoob.gdd.service.UserService;

import javax.annotation.Resource;


/**
 * 用户模块
 */
@CrossOrigin(origins = "http://127.0.0.1:4200")
@RequestMapping("/user")
@RestController
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private EmailService emailService;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Object register(@RequestBody User user) throws Exception {
        return userService.register(user);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user) throws Exception {
        return userService.login(user);
    }

}
