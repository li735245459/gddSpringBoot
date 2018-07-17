package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.mapper.GoodsTypeMapper;
import snoob.gdd.model.CoverType;
import snoob.gdd.model.GoodsType;
import snoob.gdd.service.GoodsService;
import snoob.gdd.util.ResultUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    /**
     * 编辑、添加商品类型信息
     *
     * @param goodsType
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyGoodsType(GoodsType goodsType) throws Exception {
        if (goodsType.getId() == null) {
            /**
             * 添加
             */
            goodsTypeMapper.insertSelective(goodsType);
        } else {
            /**
             * 编辑
             */
            goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
        }
        return ResultUtil.success();
    }

    /**
     * 分页查询商品类型信息
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object findGoodsType() {
        Example example = new Example(CoverType.class);
        // 根据nodeLevel降序排列(用于创建tree格式数据)
        // 根据createTime升序(用于排列节点和子节点的顺序)
        example.orderBy("nodeLevel").desc().orderBy("createTime").asc();
        List<GoodsType> coverTypes = goodsTypeMapper.selectByExample(example);
        return ResultUtil.success(coverTypes);
    }

    /**
     * 删除商品类型信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object deleteGoodsType(String id) throws Exception {
        List<String> ids = Arrays.asList(id.split(","));
        goodsTypeMapper.customDelete(ids);
        return ResultUtil.success();
    }
}
