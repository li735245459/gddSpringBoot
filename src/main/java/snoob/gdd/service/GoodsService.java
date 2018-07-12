package snoob.gdd.service;

import snoob.gdd.model.GoodsType;

public interface GoodsService {
    Object modify(GoodsType goodsType) throws Exception;
    Object page(GoodsType goodsType, Integer pageNumber, Integer pageSize) throws Exception;
    Object delete(String id) throws Exception;
}
