package snoob.gdd.mapper;

import snoob.gdd.model.GoodsType;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface GoodsTypeMapper extends Mapper<GoodsType>, MySqlMapper<GoodsType> {
    /**
     * 根据ID批量删除商品类型信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;
}