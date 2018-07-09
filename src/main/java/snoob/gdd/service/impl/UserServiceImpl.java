package snoob.gdd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.JwtUtil;
import snoob.gdd.util.OnlyUtil;
import snoob.gdd.util.ResultUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OnlyUtil onlyUtil;

    /**
     * 添加、编辑
     *
     * @param user
     * @return
     */
    @Override
    public Object modify(User user) {
        if (user.getPhone() != null && onlyUtil.phoneUsed(user.getPhone())) {
            return ResultUtil.error(ResultEnum.ERROR_PHONE_USED);
        }
        if (user.getEmail() != null && onlyUtil.emailUsed(user.getEmail())) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_USED);
        }
        if (user.getId() == null) {
            /**
             * 添加
             */
            userMapper.insertSelective(user);
        } else {

            /**
             * 编辑
             */
            userMapper.updateByPrimaryKeySelective(user);
        }
        return ResultUtil.success();
    }

    /**
     * 通过email修改密码
     *
     * @param user
     * @return
     */
    @Override
    public Object modifyPassword(User user) {
        User item = new User();
        item.setEmail(user.getEmail());
        item = userMapper.selectOne(item);
        if (item == null) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_ILLEGAL);
        }
        user.setId(item.getId());
        user.setEmail(null);
        userMapper.updateByPrimaryKeySelective(user);
        return ResultUtil.success();
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public Object login(User user) {
        String ip = user.getLoginIp();
        user.setLoginIp(null);
        User item = userMapper.selectOne(user);
        if (item == null) {
            // 用户名或者密码错误
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_OR_PASSWORD);
        }
        // 登陆成功
        item.setLoginTime(new Date());
        item.setLoginIp(ip);
        userMapper.updateByPrimaryKeySelective(item);
        // 创建JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put("aud", item.getId());
        claims.put("roles", "add;delete;update;select;");
        Map<String, Object> data = new HashMap<>();
        data.put("jwt", JwtUtil.encodeJWT(claims));
        data.put("name", item.getName());
        data.put("cover", item.getCover());
        return ResultUtil.success(data);
    }

    @Override
    public Object checkJWT(String jwt) {
        return JwtUtil.checkJWT(jwt);
    }

    @Override
    public Object home(String userId) {
        List<User> users = userMapper.select(new User());
        return ResultUtil.success(users.get(0));
    }

    /**
     * 分页查询
     *
     * @param user
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Object page(User user, Integer pageNumber, Integer pageSize) {
        Example example = new Example(User.class);
//        // 设置行级锁,确保数据一致性
//        example.setForUpdate(true);
//        // 去重
//        example.setDistinct(true);
//        // 指定查询列
//        example.selectProperties("id", "name");
//        // 设置排序规则 .orderBy("loginTime").asc();
        example.orderBy("createTime").asc();
        Example.Criteria criteria = example.createCriteria();
        if (user.getName() != null) {
            criteria.andLike("name", user.getName().trim() + "%");
        }
        if (user.getSex() != null && !"0".equals(user.getSex())) {
            criteria.andEqualTo("sex", user.getSex().trim());
        }
        if (user.getEmail() != null) {
            criteria.andEqualTo("email", user.getEmail().trim());
        }
        if (user.getPhone() != null) {
            criteria.andEqualTo("phone", user.getPhone().trim());
        }
        if (user.getCreateTime() != null) {
            criteria.andGreaterThanOrEqualTo("createTime", user.getCreateTime());
        }
        if (user.getLoginTime() != null) {
            criteria.andGreaterThanOrEqualTo("loginTime", user.getLoginTime());
        }
        // 开启分页模式
        PageHelper.startPage(pageNumber, pageSize);
        List<User> users = userMapper.selectByExample(example);
        // 获取分页对象
        PageInfo page = new PageInfo(users);
        return ResultUtil.success(page);
    }

    /**
     * 删除
     * id = "3b2ebfa1-ed59-4091-a800-aef6e867f1a1" 表示单一删除
     * id = "3b2ebfa1-ed59-4091-a800-aef6e867f1a1,3b2ebfa1-ed59-4091-a800-aef6e867f1a2" 表示批量删除
     * id = "all" 表示清空
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        if ("all".equals(id)) { // 删除所有
            userMapper.delete(new User());
        } else { // 删除所选(批量)
            List<String> ids = Arrays.asList(id.split(","));
            userMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }
}
