package snoob.gdd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.UserService;
import snoob.gdd.util.JwtUtil;
import snoob.gdd.util.ResultUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 检查邮箱是否合法
     *
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public Boolean checkEmail(String email) throws Exception {
        User item = new User();
        item.setEmail(email);
        List<User> items = userMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在,合法
        } else {
            return true; // 存在,不合法
        }
    }

    /**
     * 检查手机号码是否合法
     *
     * @param phone
     * @return
     * @throws Exception
     */
    @Override
    public Boolean checkPhone(String phone) throws Exception {
        User item = new User();
        item.setPhone(phone);
        List<User> items = userMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在,合法
        } else {
            return true; // 存在,不合法
        }
    }

    /**
     * 用户登陆
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Object login(User user) throws Exception {
        String ip = user.getLoginIp();
        user.setLoginIp(null);
        User item = userMapper.selectOne(user);
        // 用户名或者密码错误
        if (item == null) {
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

    /**
     * 分页查询用户
     *
     * @param user
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object page(User user, Integer pageNumber, Integer pageSize) throws Exception {
        Example example = new Example(User.class);
//        example.setForUpdate(true); // 设置行级锁,确保数据一致性
//        example.setDistinct(true); // 去重
//        example.selectProperties("id", "name"); // 指定查询列
        example.orderBy("createTime").asc(); // 设置排序规则
        Example.Criteria criteria = example.createCriteria(); // 动态查询条件
        if (user.getName() != null) {
            criteria.andLike("name", user.getName().trim() + "%"); // name like 'aa'%
        }
        if (user.getSex() != null && !"0".equals(user.getSex())) {
            criteria.andEqualTo("sex", user.getSex().trim()); // sex = 'aa'
        }
        if (user.getEmail() != null) {
            criteria.andEqualTo("email", user.getEmail().trim()); // email = 'aa'
        }
        if (user.getPhone() != null) {
            criteria.andEqualTo("phone", user.getPhone().trim()); // phone = 'aa'
        }
        if (user.getCreateTime() != null) {
            criteria.andGreaterThanOrEqualTo("createTime", user.getCreateTime()); // createTime >= '1992-10-29 10:10:10'
        }
        if (user.getLoginTime() != null) {
            criteria.andGreaterThanOrEqualTo("loginTime", user.getLoginTime()); // loginTime >= '1992-10-29 10:10:10'
        }
        // 开启分页模式
        PageHelper.startPage(pageNumber, pageSize);
        List<User> users = userMapper.selectByExample(example);
        // 获取分页对象
        PageInfo page = new PageInfo(users);
        return ResultUtil.success(page);
    }

    /**
     * 添加、修改用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Object modify(User user) throws Exception {
        if (user.getPhone() != null && checkPhone(user.getPhone())) {
            return ResultUtil.error(ResultEnum.ERROR_PHONE_USED);
        }
        if (user.getEmail() != null && checkEmail(user.getEmail())) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_USED);
        }
        if (user.getId() == null) {
            /*添加*/
            userMapper.insertSelective(user);
        } else {
            /*编辑*/
            userMapper.updateByPrimaryKeySelective(user);
        }
        return ResultUtil.success();
    }

    /**
     * 根据邮箱修改密码
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyPassword(User user) throws Exception {
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
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除用户
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        if ("all".equals(id)) {
            userMapper.delete(new User());
        } else {
            List<String> ids = Arrays.asList(id.split(","));
            userMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }
}
