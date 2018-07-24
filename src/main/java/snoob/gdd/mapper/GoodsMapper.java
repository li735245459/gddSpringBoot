package snoob.gdd.mapper;

import snoob.gdd.model.Goods;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface GoodsMapper extends Mapper<Goods>, MySqlMapper<Goods> {
}