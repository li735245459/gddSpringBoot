package snoob.gdd.util;

import org.springframework.stereotype.Component;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * 全局唯一性检查
 */
@Component
public class OnlyUtil {

    @Resource
    private UserMapper userMapper;

    /**
     * 邮箱是否存在
     * @return
     */
    public Boolean emailUsed(String email) {
        User item = new User();
        item.setEmail(email);
        List<User> items = userMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在
        } else {
            return true; // 存在
        }
    }

    /**
     * 号码是否存在
     * @param phone
     * @return
     */
    public Boolean phoneUsed(String phone) {
        User item = new User();
        item.setPhone(phone);
        List<User> items = userMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在
        } else {
            return true; // 存在
        }
    }
}
