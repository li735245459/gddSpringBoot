package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;

import javax.annotation.Resource;

/**
 * 用户模块
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

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

    /**
     * 修改密码
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PutMapping("/modifyPassword")
    public Object modifyPassword(@RequestBody User user) throws Exception {
        return userService.modifyPassword(user);
    }

    /**
     * 检查jwt是否有效
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    @GetMapping("/checkJwt/{jwt}/{email}")
    public Object checkJwt(
            @PathVariable(value = "jwt") String jwt,
            @PathVariable(value = "email") String email) throws Exception {
        return userService.checkJwt(jwt, email);
    }

}
