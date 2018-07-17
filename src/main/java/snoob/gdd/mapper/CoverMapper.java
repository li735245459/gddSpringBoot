package snoob.gdd.mapper;

import snoob.gdd.model.Cover;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface CoverMapper extends Mapper<Cover>, MySqlMapper<Cover> {
    /**
     * 根据ID批量删除封面信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;
}