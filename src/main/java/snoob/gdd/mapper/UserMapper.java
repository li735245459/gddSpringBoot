package snoob.gdd.mapper;

import snoob.gdd.model.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    /**
     * 根据ID批量删除用户信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;

    /**
     * 批量添加用户信息
     * @param users
     * @throws Exception
     */
    void customInsert(List<User> users) throws Exception;
}