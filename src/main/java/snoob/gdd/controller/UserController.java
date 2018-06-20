package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import snoob.gdd.model.Page;

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
    public Object login(@RequestBody User user, HttpServletRequest request) throws Exception {
        user.setLoginIp(request.getAttribute("ip").toString());
        return userService.login(user);
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/modifyPassword")
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
    @GetMapping("/checkJwt/{jwt}")
    public Object checkJwt(
            @PathVariable(value = "jwt") String jwt) throws Exception {
        return userService.checkJWT(jwt);
    }

    /**
     * 门户网站首页
     *
     * @return
     */
    @GetMapping("/home")
    public Object home(HttpServletRequest request) throws Exception {
        return userService.home(request.getAttribute("userId").toString());
    }


    /**
     * 分页查询
     * @return
     */
    @GetMapping("/queryByPage")
    public Object queryByPage() throws Exception {
        return userService.userByPage(new Page());
    }

}
