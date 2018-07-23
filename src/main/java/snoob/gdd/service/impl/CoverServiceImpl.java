package snoob.gdd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.mapper.CoverMapper;
import snoob.gdd.mapper.CoverTypeMapper;
import snoob.gdd.model.Cover;
import snoob.gdd.model.CoverType;
import snoob.gdd.model.User;
import snoob.gdd.service.CoverService;
import snoob.gdd.util.OnlyUtil;
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

    @Resource
    private OnlyUtil onlyUtil;

    /**
     * 查询封面类型(前端根据parentId以Tree的形式展示)
     * 按照nodeLevel(0表示根节点,1表示一级子节点...)降序排列,方便从最低层的节点依次向上关联父节点
     * 按照createTime升序排列来构建子节排列顺序
     *
     * @return
     */
    @Override
    public Object selectCoverType() {
        Example example = new Example(CoverType.class);
        example.orderBy("nodeLevel").desc().orderBy("createTime").asc();
        List<CoverType> coverTypes = coverTypeMapper.selectByExample(example);
        return ResultUtil.success(coverTypes);
    }

    /**
     * 1) 添加封面类型,名称不能重复
     * 2) 编辑封面类型,名称不能重复
     * 修改前需要级联修改封面信息的coverTypeName字段
     *
     * @param coverType
     * @return
     */
    @Override
    @Transactional
    public Object modifyCoverType(CoverType coverType) {
        /*校验封面类型名称*/
        if (coverType.getName() != null && onlyUtil.CoverTypeNameUsed(coverType.getName())) {
            return ResultUtil.error(ResultEnum.ERROR_COVERTYPENAME_USED);
        }
        if (coverType.getId() == null) {
            /*添加*/
            coverTypeMapper.insertSelective(coverType);
        } else {
            /*编辑*/
            /*根据id查询coverType表的name(oldCoverTypeName)*/
            CoverType coverTypeItem = new CoverType();
            coverTypeItem.setId(coverType.getId());
            coverTypeItem = coverTypeMapper.selectOne(coverTypeItem);
            String oldCoverTypeName = coverTypeItem.getName(); // 修改前的CoverTypeName
            String newCoverTypeName = coverType.getName(); // 修改后的CoverTypeName
            /*封面类型的名称发送改变*/
            if (!oldCoverTypeName.equals(newCoverTypeName)) {
                Cover coverItem = new Cover();
                coverItem.setCoverTypeName(newCoverTypeName);
                Example example = new Example(Cover.class);
                Example.Criteria criteria = example.createCriteria(); // 动态查询条件
                criteria.andEqualTo("coverTypeName", oldCoverTypeName);
                /*根据oldCoverTypeName修改封面的coverTypeName*/
                coverMapper.updateByExampleSelective(coverItem, example);
                /*修改封面类型*/
                coverTypeMapper.updateByPrimaryKeySelective(coverType);
            }
        }
        return ResultUtil.success();
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Object deleteCoverType(String id) throws Exception {
        List<String> ids = Arrays.asList(id.split(","));
        coverTypeMapper.customDelete(ids);
        /*
         *级联制空cover表中的coverTypeName字段(涉及到事务)
         */
        return ResultUtil.success();
    }

    @Override
    public Object pageCover(Cover cover, Integer pageNumber, Integer pageSize) {
        Example example = new Example(Cover.class);
        example.orderBy("createTime").asc();
        // 动态sql
        Example.Criteria criteria = example.createCriteria();
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

    @Override
    public Object modifyCover(Cover cover) {
        if (cover.getName() != null && onlyUtil.CoverNameUsed(cover.getName())) {
            return ResultUtil.error(ResultEnum.ERROR_COVERNAME_USED);
        }
        if (cover.getId() == null) {
            /*添加*/
            coverMapper.insertSelective(cover);
        } else {
            /*编辑*/
            coverMapper.updateByPrimaryKeySelective(cover);
        }
        return ResultUtil.success();
    }

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
