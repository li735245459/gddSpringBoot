package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
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

    @GetMapping("/sendEmail/{email}/{codeType}")
    public Object sendEmail(
            @PathVariable(value = "email", required = true) String email,
            @PathVariable(value = "codeType", required = true) String codeType){
        return userService.sendEmail(email, codeType);
    }
}
