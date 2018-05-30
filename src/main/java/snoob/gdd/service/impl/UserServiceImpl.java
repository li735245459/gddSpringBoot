package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.Message;
import snoob.gdd.util.RandomString;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userDao;

    private Message message = new Message();

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public Object register(User user) {
        if(userDao.select(new User(user.getEmail())).isEmpty()) {
            String id = RandomString.getUuidStr();
            user.setId(id);
            Integer result = userDao.insertSelective(user);
            if(result.equals(1)){
                message.setCode(1);
                message.setContent("注册成功");
            }else{
                message.setCode(0);
                message.setContent("注册失败");
            }
        }else{
            message.setCode(-1);
            message.setContent("该邮箱已被注册");
        }
        return message;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public Object login(User user) {
        if(userDao.select(user).isEmpty()){
            message.setCode(1);
            message.setContent("登录成功");
        }else{
            message.setCode(0);
            message.setContent("邮箱或者密码错误");
        }
        return message;
    }
}
