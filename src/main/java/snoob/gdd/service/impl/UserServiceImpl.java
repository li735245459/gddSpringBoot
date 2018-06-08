package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.GlobalCustomException;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.JwtUtil;
import snoob.gdd.util.ResultUtil;
import snoob.gdd.util.StrUtil;

import javax.annotation.Resource;
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
    public Object register(User user) {
        User userByEmail = new User();
        userByEmail.setEmail(user.getEmail());
        if (userDao.select(userByEmail).isEmpty()) {
            user.setId(StrUtil.getUuidStr());
            userDao.insertSelective(user);
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
        List<User> users = userDao.select(user);
        if (users.isEmpty()) {
            // 用户名或者密码错误
            throw new GlobalCustomException(ResultEnum.ERROR_LOGIN_VALIDATE);
        } else {
            User loginUser = users.get(0);
            // 创建JWT
            Map<String, Object> claims = new HashMap<>();
            claims.put("aud", loginUser.getId());
            claims.put("secret", loginUser.getSecret());
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
        userDao.updateByPrimaryKeySelective(user);
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
     * 首页
     *
     * @param userId
     * @return
     */
    @Override
    public Object home(String userId) {
        List<User> users = userDao.select(new User());//selectByExample
        return ResultUtil.success(users.get(0));
    }


}
