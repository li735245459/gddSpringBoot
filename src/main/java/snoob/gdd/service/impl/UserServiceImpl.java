package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.exception.GddException;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.RandomStrUtil;
import snoob.gdd.util.ResultUtil;

import javax.annotation.Resource;

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
    public Object register(User user) throws Exception{
        if (userDao.select(new User(user.getEmail())).isEmpty()) {
            user.setId(RandomStrUtil.getUuidStr());
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
    public Object login(User user) throws Exception{
        if (userDao.select(user).isEmpty()) {
            // 用户名或者密码错误
            throw new GddException(ResultEnum.ERROR_LOGIN_VALIDATE);
        } else {
            // 登陆成功
            return ResultUtil.success();
        }
    }
}
