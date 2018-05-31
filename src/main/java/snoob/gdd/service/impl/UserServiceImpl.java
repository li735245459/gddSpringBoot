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
            if (userDao.insertSelective(user) > 0) {
                return ResultUtil.success();
            } else {
                throw new GddException(ResultEnum.REGISTER_FAILED);
            }
        } else {
            throw new GddException(ResultEnum.EMAIL_REFISTERED);
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
            throw new GddException(ResultEnum.LOFIN_FAILED);
        } else {
            return ResultUtil.success();
        }
    }
}
