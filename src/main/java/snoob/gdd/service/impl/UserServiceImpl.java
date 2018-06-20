package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.GlobalCustomException;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.Page;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.JwtUtil;
import snoob.gdd.util.ResultUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public Object register(User user) {
        User selectEmail = new User();
        selectEmail.setEmail(user.getEmail());
        List<User> checkEmailResult = userMapper.select(selectEmail);
        if (checkEmailResult.isEmpty()) { // size=0为true,size>0为false
            userMapper.insertSelective(user); //选择性的插入存在值的字段
            return ResultUtil.success();
        } else {
            // 邮箱已被注册
            throw new GlobalCustomException(ResultEnum.ERROR_EMAIL_ONLY_VALIDATE);
        }
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public Object login(User user) {
        String ip = user.getLoginIp();
        user.setLoginIp(null);
        List<User> loginResult = userMapper.select(user);
        if (loginResult.isEmpty()) {
            // 用户名或者密码错误
            throw new GlobalCustomException(ResultEnum.ERROR_LOGIN_VALIDATE);
        } else {
            // 登陆成功
            loginResult.get(0).setLoginTime(new Date());
            loginResult.get(0).setLoginIp(ip);
            userMapper.updateByPrimaryKey(loginResult.get(0));
            // 创建JWT
            Map<String, Object> claims = new HashMap<>();
            claims.put("aud", loginResult.get(0).getId());
            claims.put("roles", "add;delete;update;select;");
            Map<String, Object> data = new HashMap<>();
            data.put("jwt", JwtUtil.encodeJWT(claims));
            return ResultUtil.success(data);
        }
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyPassword(User user) {
        User selectEmail = new User();
        selectEmail.setEmail(user.getEmail());
        List<User> checkEmailResult = userMapper.select(selectEmail);
        if (checkEmailResult.isEmpty()) {
            throw new GlobalCustomException(ResultEnum.ERROR_EMAIL);
        }
        user.setId(checkEmailResult.get(0).getId());
        userMapper.updateByPrimaryKeySelective(user);
        return ResultUtil.success();
    }

    /**
     * 检查jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    @Override
    public Object checkJWT(String jwt) {
        return JwtUtil.checkJWT(jwt);
    }

    /**
     * 门户网站首页
     *
     * @param userId
     * @return
     */
    @Override
    public Object home(String userId) {
        List<User> users = userMapper.select(new User());//selectByExample
        return ResultUtil.success(users.get(0));
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public Object userByPage(Page page) {
        System.out.print(userMapper.selectAll().get(0).getCreateTime());
        return ResultUtil.success(userMapper.selectAll());
    }

}
