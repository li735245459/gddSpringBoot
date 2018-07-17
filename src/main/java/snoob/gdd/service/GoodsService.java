package snoob.gdd.service;

import snoob.gdd.model.GoodsType;

public interface GoodsService {
    Object modifyGoodsType(GoodsType goodsType) throws Exception;
    Object findGoodsType() throws Exception;
    Object deleteGoodsType(String id) throws Exception;
}
