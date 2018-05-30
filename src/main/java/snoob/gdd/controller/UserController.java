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
     * 添加用户信息
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Object register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        return userService.login(user);
    }
}
