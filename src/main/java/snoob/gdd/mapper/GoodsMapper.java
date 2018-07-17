package snoob.gdd.mapper;

import snoob.gdd.model.Goods;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface GoodsMapper extends Mapper<Goods>, MySqlMapper<Goods> {
    /**
     * 根据ID批量删除商品信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    void customDelete(List<String> ids) throws Exception;
}