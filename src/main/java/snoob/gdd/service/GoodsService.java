package snoob.gdd.service;

import snoob.gdd.model.Goods;
import snoob.gdd.model.GoodsType;

public interface GoodsService {
    // 商品类型
    Object modifyGoodsType(GoodsType goodsType) throws Exception;
    Object findGoodsType() throws Exception;
    Object deleteGoodsType(String id) throws Exception;
    // 商品
    Object modifyGoods(Goods goods) throws Exception;
    Object pageGoods(Goods goods, Integer pageNumber, Integer pageSize) throws Exception;
    Object deleteGoods(String id) throws Exception;
}
