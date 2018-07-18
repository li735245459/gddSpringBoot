package snoob.gdd.util;

import org.springframework.stereotype.Component;
import snoob.gdd.mapper.CoverMapper;
import snoob.gdd.mapper.CoverTypeMapper;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.Cover;
import snoob.gdd.model.CoverType;
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

    @Resource
    private CoverTypeMapper coverTypeMapper;

    @Resource
    private CoverMapper coverMapper;

    /**
     * 用户模块-邮箱是否存在
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
     * 用户模块-号码是否存在
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

    /**
     * 封面模块-封面类型名称是否存在
     * @param coverTypeName
     * @return
     */
    public Boolean CoverTypeNameUsed(String coverTypeName) {
        CoverType item = new CoverType();
        item.setName(coverTypeName);
        List<CoverType> items = coverTypeMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在
        } else {
            return true; // 存在
        }
    }

    /**
     * 封面模块-封面名称是否存在
     * @param coverName
     * @return
     */
    public Boolean CoverNameUsed(String coverName) {
        Cover item = new Cover();
        item.setName(coverName);
        List<Cover> items = coverMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在
        } else {
            return true; // 存在
        }
    }
}
