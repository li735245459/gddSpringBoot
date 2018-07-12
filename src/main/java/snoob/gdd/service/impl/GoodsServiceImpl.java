package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.model.GoodsType;
import snoob.gdd.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {

    /**
     * 编辑、添加商品类型信息
     *
     * @param goodsType
     * @return
     * @throws Exception
     */
    @Override
    public Object modify(GoodsType goodsType) throws Exception {
        return null;
    }

    /**
     * 分页查询商品类型信息
     *
     * @param goodsType
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object page(GoodsType goodsType, Integer pageNumber, Integer pageSize) throws Exception {
        return null;
    }

    /**
     * 删除商品类型信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        return null;
    }
}
