package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.Message;
import snoob.gdd.util.RandomString;

import javax.annotation.Resource;

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
            user.setId(RandomString.getUuidStr());
            Integer result = userDao.insertSelective(user);
            if(result.equals(1)){
                message.setMessage("注册成功");
                message.setCode(1);
            }else{
                message.setMessage("注册失败");
                message.setCode(0);
            }
        }else{
            message.setMessage("该邮箱已被注册");
            message.setCode(2);
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
            message.setCode(2);
            message.setMessage("邮箱或者密码错误");
        }else{
            message.setCode(1);
            message.setMessage("登录成功");
        }
        return message;
    }

    @Override
    public Object sendEmail(String email, String codeType) {
        return null;
    }
}
