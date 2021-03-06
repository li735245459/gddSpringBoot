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
     * 查询商品分类
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object selectGoodsType() throws Exception {
        Example example = new Example(CoverType.class);
        // 根据nodeLevel降序排列(用于创建tree格式数据)
        // 根据createTime升序(用于排列节点和子节点的顺序)
        example.orderBy("nodeLevel").desc().orderBy("createTime").asc();
        List<GoodsType> coverTypes = goodsTypeMapper.selectByExample(example);
        return ResultUtil.success(coverTypes);
    }

    /**
     * 分页查询商品
     *
     * @param goods
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object pageGoods(Goods goods, Integer pageNumber, Integer pageSize) throws Exception {
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
     * 添加、编辑商品分类
     *
     * @param goodsType
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyGoodsType(GoodsType goodsType) throws Exception {
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
     * 编辑、添加商品
     *
     * @param goods
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyGoods(Goods goods) throws Exception {
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
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除商品分类
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object deleteGoodsType(String id) throws Exception {
        List<String> ids = Arrays.asList(id.split(",")); // 需要删除的商品分类id集合
        Example example = new Example(GoodsType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        goodsTypeMapper.deleteByExample(example);
        return ResultUtil.success();
    }

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除商品
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object deleteGoods(String id) throws Exception {
        if ("all".equals(id)) {
            goodsMapper.delete(new Goods());
        } else {
            List<String> ids = Arrays.asList(id.split(",")); // 需要删除的商品id集合
            Example example = new Example(Goods.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andIn("id", ids);
            goodsMapper.deleteByExample(example);
        }
        return ResultUtil.success();
    }
}
