package snoob.gdd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import snoob.gdd.mapper.GoodsMapper;
import snoob.gdd.mapper.GoodsTypeMapper;
import snoob.gdd.model.CoverType;
import snoob.gdd.model.Goods;
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

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 编辑、添加商品类型信息
     *
     * @param goodsType
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyGoodsType(GoodsType goodsType) {
        if (goodsType.getId() == null) {
            /*添加*/
            goodsTypeMapper.insertSelective(goodsType);
        } else {
            /*编辑*/
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
    public Object selectGoodsType() {
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

    /**
     * 修改、添加商品信息
     *
     * @param goods
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyGoods(Goods goods) {
        if (goods.getId() == null) {
            /*添加*/
            goodsMapper.insertSelective(goods);
        } else {
            /*编辑*/
            goodsMapper.updateByPrimaryKeySelective(goods);
        }
        return ResultUtil.success();
    }

    /**
     * 分页查询商品信息
     *
     * @param goods
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object pageGoods(Goods goods, Integer pageNumber, Integer pageSize) {
        Example example = new Example(Goods.class);
        example.orderBy("createTime").asc();
        // 动态sql
        Example.Criteria criteria = example.createCriteria();
        if (goods.getName() != null) {
            criteria.andLike("name", goods.getName());
        }
        // 开启分页模式
        PageHelper.startPage(pageNumber, pageSize);
        List<Goods> users = goodsMapper.selectByExample(example);
        // 获取分页对象
        PageInfo page = new PageInfo(users);
        return ResultUtil.success(page);
    }

    /**
     * 删除商品信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object deleteGoods(String id) throws Exception {
        if ("all".equals(id)) {
            /*删除所有*/
            goodsMapper.delete(new Goods());
        } else {
            /*删除所选(批量)*/
            List<String> ids = Arrays.asList(id.split(","));
            goodsMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }


}
