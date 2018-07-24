package snoob.gdd.service;

import snoob.gdd.model.Goods;
import snoob.gdd.model.GoodsType;

/**
 * 商品接口
 */
public interface GoodsService {
    /**
     * 查询商品分类
     * @return
     * @throws Exception
     */
    Object selectGoodsType() throws Exception;

    /**
     * 分页查询商品
     * @param goods
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    Object pageGoods(Goods goods, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 添加、编辑商品分类
     * @param goodsType
     * @return
     * @throws Exception
     */
    Object modifyGoodsType(GoodsType goodsType) throws Exception;

    /**
     * 编辑、添加商品
     * @param goods
     * @return
     * @throws Exception
     */
    Object modifyGoods(Goods goods) throws Exception;

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除商品分类
     * @param id
     * @return
     * @throws Exception
     */
    Object deleteGoodsType(String id) throws Exception;

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除商品
     * @param id
     * @return
     * @throws Exception
     */
    Object deleteGoods(String id) throws Exception;
}
