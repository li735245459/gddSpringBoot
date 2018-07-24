package snoob.gdd.service;

import snoob.gdd.model.User;

/**
 * 用户接口
 */
public interface UserService {

    /**
     * 检查邮箱是否合法
     * @param email
     * @return
     * @throws Exception
     */
    Boolean checkEmail(String email) throws Exception;

    /**
     * 检查手机号码是否合法
     * @param phone
     * @return
     * @throws Exception
     */
    Boolean checkPhone(String phone) throws Exception;

    /**
     * 用户登陆
     * @param user
     * @return
     * @throws Exception
     */
    Object login(User user) throws Exception;

    /**
     * 分页查询用户
     * @param user
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    Object page(User user, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 添加、修改用户
     * @param user
     * @return
     * @throws Exception
     */
    Object modify(User user) throws Exception;

    /**
     * 根据邮箱修改密码
     * @param user
     * @return
     * @throws Exception
     */
    Object modifyPassword(User user) throws Exception;

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除用户
     * @param id
     * @return
     * @throws Exception
     */
    Object delete(String id) throws Exception;
}
