package snoob.gdd.mapper;

import snoob.gdd.model.User;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;
}