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
        if (user.getEmail() != null && onlyUtil.emailUsed(user.getEmail())) {
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_USED);
        }
        if (user.getPhone() != null && onlyUtil.phoneUsed(user.getPhone())) {
            return ResultUtil.error(ResultEnum.ERROR_PHONE_USED);
        }
        if (user.getId() == null) {
            /**
             * 添加
             */
            userMapper.insertSelective(user);
        } else {

            /**
             * 修改
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
        List<User> loginResult = userMapper.select(user);
        if (loginResult.isEmpty()) {
            // 用户名或者密码错误
            return ResultUtil.error(ResultEnum.ERROR_EMAIL_OR_PASSWORD);
        } else {
            // 登陆成功
            loginResult.get(0).setLoginTime(new Date());
            loginResult.get(0).setLoginIp(ip);
            userMapper.updateByPrimaryKey(loginResult.get(0));
            // 创建JWT
            Map<String, Object> claims = new HashMap<>();
            claims.put("aud", loginResult.get(0).getId());
            claims.put("roles", "add;delete;update;select;");
            Map<String, Object> data = new HashMap<>();
            data.put("jwt", JwtUtil.encodeJWT(claims));
            data.put("name", loginResult.get(0).getName());
            data.put("cover", loginResult.get(0).getCover());
            return ResultUtil.success(data);
        }
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
        System.out.println("pageNumber-" + pageNumber);
        System.out.println("pageSize-" + pageSize);
        System.out.println("---------------------");
        PageHelper.startPage(pageNumber, pageSize);
        List<User> users = userMapper.selectAll();
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
