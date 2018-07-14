package snoob.gdd.mapper;

import snoob.gdd.model.CoverType;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface CoverTypeMapper extends Mapper<CoverType>, MySqlMapper<CoverType> {
    /**
     * 根据ID批量删除用户信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;
}