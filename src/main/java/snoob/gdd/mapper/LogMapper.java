package snoob.gdd.mapper;

import snoob.gdd.model.Log;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface LogMapper extends Mapper<Log>, MySqlMapper<Log> {
    /**
     * 根据ID批量删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;
}