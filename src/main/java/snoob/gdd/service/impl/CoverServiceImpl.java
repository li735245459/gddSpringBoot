package snoob.gdd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import snoob.gdd.mapper.CoverMapper;
import snoob.gdd.mapper.CoverTypeMapper;
import snoob.gdd.model.Cover;
import snoob.gdd.model.CoverType;
import snoob.gdd.service.CoverService;
import snoob.gdd.util.ResultUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class CoverServiceImpl implements CoverService {

    @Resource
    private CoverTypeMapper coverTypeMapper;

    @Resource
    private CoverMapper coverMapper;

    /**
     * 查询封面类型信息
     *
     * @return
     */
    @Override
    public Object selectCoverType() {
        Example example = new Example(CoverType.class);
        // 根据nodeLevel降序排列(用于创建tree格式数据)
        // 根据createTime升序(用于排列节点和子节点的顺序)
        example.orderBy("nodeLevel").desc().orderBy("createTime").asc();
        List<CoverType> coverTypes = coverTypeMapper.selectByExample(example);
        return ResultUtil.success(coverTypes);
    }

    /**
     * 编辑、添加封面类型信息
     *
     * @param coverType
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyCoverType(CoverType coverType) {
        if (coverType.getId() == null) {
            /*添加*/
            coverTypeMapper.insertSelective(coverType);
        } else {
            /*编辑*/
            coverTypeMapper.updateByPrimaryKeySelective(coverType);
        }
        return ResultUtil.success();
    }

    /**
     * 删除封面类型信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object deleteCoverType(String id) throws Exception {
        List<String> ids = Arrays.asList(id.split(","));
        coverTypeMapper.customDelete(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询封面信息
     *
     * @param cover
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object pageCover(Cover cover, Integer pageNumber, Integer pageSize) {
        Example example = new Example(Cover.class);
        example.orderBy("createTime").asc();
        // 动态sql
        Example.Criteria criteria = example.createCriteria();
        if (cover.getCoverTypeId() != null) {
            criteria.andEqualTo("coverTypeId", cover.getCoverTypeId());
        }
        if (cover.getName() != null) {
            criteria.andLike("name", cover.getName());
        }
        // 开启分页模式
        PageHelper.startPage(pageNumber, pageSize);
        List<Cover> users = coverMapper.selectByExample(example);
        // 获取分页对象
        PageInfo page = new PageInfo(users);
        return ResultUtil.success(page);
    }

    /**
     * 修改、添加封面信息
     *
     * @param cover
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyCover(Cover cover) {
        if (cover.getId() == null) {
            /*添加*/
            coverMapper.insertSelective(cover);
        } else {
            /*编辑*/
            coverMapper.updateByPrimaryKeySelective(cover);
        }
        return ResultUtil.success();
    }

    /**
     * 删除封面信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object deleteCover(String id) throws Exception {
        if ("all".equals(id)) {
            /*删除所有*/
            coverMapper.delete(new Cover());
        } else {
            /*删除所选(批量)*/
            List<String> ids = Arrays.asList(id.split(","));
            coverMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }
}
