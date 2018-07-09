package snoob.gdd.mapper;

import snoob.gdd.model.EmailCode;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EmailCodeMapper extends Mapper<EmailCode> {
    /**
     * 根据ID批量删除用户信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;
}