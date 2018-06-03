package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.exception.GddException;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.JwtUtil;
import snoob.gdd.util.StrUtil;
import snoob.gdd.util.ResultUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userDao;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public Object register(User user) throws Exception {
        if (userDao.select(new User(user.getEmail())).isEmpty()) {
            user.setId(StrUtil.getUuidStr());
            user.setSecret(StrUtil.getMd5Str(user.getEmail()));
            userDao.insertSelective(user);
            // 注册成功
            return ResultUtil.success();
        } else {
            // 邮箱已被注册
            throw new GddException(ResultEnum.ERROR_EMAIL_ONLY_VALIDATE);
        }
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public Object login(User user) throws Exception {
        List<User> users = userDao.select(user);
        if (users.isEmpty()) {
            // 用户名或者密码错误
            throw new GddException(ResultEnum.ERROR_LOGIN_VALIDATE);
        } else {
            // 登陆成功
            User loginUser = users.get(0);
            // 创建JWT
            Map<String,Object> claims = new HashMap<String,Object>();
            claims.put("secret", loginUser.getSecret());
            claims.put("iat", new Date(System.currentTimeMillis()));
            claims.put("exp", new Date(System.currentTimeMillis() + 1000*60));
            String jwt = JwtUtil.encodeJWT(claims);
            return ResultUtil.success(jwt);
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
    public Object modifyPassword(User user) throws Exception {
        userDao.updateByPrimaryKeySelective(user);
        // 修改成功
        return ResultUtil.success();
    }
}
