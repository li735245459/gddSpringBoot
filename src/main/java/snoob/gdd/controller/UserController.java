package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户模块
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public Object register(@RequestBody User user) throws Exception {
        return userService.modify(user);
    }

    /**
     * 添加、编辑
     *
     * @param user
     * @return
     */
    @PostMapping("/modify")
    public Object modify(@RequestBody User user) throws Exception {
        return userService.modify(user);
    }

    /**
     * 通过邮箱修改密码
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
     * 用户登录
     *
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletRequest request) throws Exception {
        user.setLoginIp(request.getAttribute("ip").toString());
        return userService.login(user);
    }

    /**
     * 分页查询
     *
     * @param user
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/page/{pageNumber}/{pageSize}")
    public Object page(@RequestBody User user,
                       @PathVariable(value = "pageNumber") Integer pageNumber,
                       @PathVariable(value = "pageSize") Integer pageSize
    ) throws Exception {
        return userService.page(user, pageNumber, pageSize);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody String id) throws Exception {
        return userService.delete(id);
    }
}
