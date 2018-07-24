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
import snoob.gdd.service.CoverService;
import snoob.gdd.util.QNYUtil;
import snoob.gdd.util.ResultUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CoverServiceImpl implements CoverService {

    @Resource
    private CoverTypeMapper coverTypeMapper;

    @Resource
    private CoverMapper coverMapper;

    /**
     * 检查封面类型名称是否合法
     *
     * @return
     * @throws Exception
     */
    @Override
    public Boolean CheckCoverTypeName(String coverTypeName) throws Exception {
        CoverType item = new CoverType();
        item.setName(coverTypeName);
        List<CoverType> items = coverTypeMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在,合法
        } else {
            return true; // 存在,不合法
        }
    }

    /**
     * 检查封面名称是否合法
     *
     * @return
     * @throws Exception
     */
    @Override
    public Boolean CheckCoverName(String coverName) throws Exception {
        Cover item = new Cover();
        item.setName(coverName);
        List<Cover> items = coverMapper.select(item);
        if (items.isEmpty()) {
            return false; // 不存在,合法
        } else {
            return true; // 存在,不合法
        }
    }

    /**
     * 查询封面类型（前端以Tree的形式展示封面分类信息，后端查询按照nodeLevel降序排列、createTime升序排列）
     *
     * @return
     * @throws Exception
     */
    @Override
    public Object selectCoverType() throws Exception {
        Example example = new Example(CoverType.class);
        example.orderBy("nodeLevel").desc().orderBy("createTime").asc();
        List<CoverType> coverTypes = coverTypeMapper.selectByExample(example);
        return ResultUtil.success(coverTypes);
    }

    /**
     * 分页查询封面
     *
     * @param cover
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object pageCover(Cover cover, Integer pageNumber, Integer pageSize) throws Exception {
        Example example = new Example(Cover.class); // 动态sql
        example.orderBy("createTime").asc(); // 按照createTime升序
        Example.Criteria criteria = example.createCriteria();
        if (cover.getName() != null) {
            criteria.andLike("name", cover.getName()); // 设置查询条件
        }
        // 开启分页模式
        PageHelper.startPage(pageNumber, pageSize);
        List<Cover> users = coverMapper.selectByExample(example);
        // 获取分页对象
        PageInfo page = new PageInfo(users);
        return ResultUtil.success(page);
    }

    /**
     * 添加、编辑封面类型
     *
     * @param coverType
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Object modifyCoverType(CoverType coverType) throws Exception {
        // 校验封面类型名称
        if (coverType.getName() != null && CheckCoverTypeName(coverType.getName())) {
            return ResultUtil.error(ResultEnum.ERROR_COVERTYPENAME_USED);
        }
        if (coverType.getId() == null) {
            /*
            添加
             */
            coverTypeMapper.insertSelective(coverType);
        } else {
            /*
            编辑
             */
            // 根据id查询coverType表的name(oldCoverTypeName)
            CoverType coverTypeItem = new CoverType();
            coverTypeItem.setId(coverType.getId());
            coverTypeItem = coverTypeMapper.selectOne(coverTypeItem);
            String oldCoverTypeName = coverTypeItem.getName(); // 修改前的CoverTypeName
            String newCoverTypeName = coverType.getName(); // 修改后的CoverTypeName
            // 封面类型的名称发生改变
            if (!oldCoverTypeName.equals(newCoverTypeName)) {
                Cover coverItem = new Cover();
                coverItem.setCoverTypeName(newCoverTypeName);
                Example example = new Example(Cover.class);
                Example.Criteria criteria = example.createCriteria(); // 动态查询条件
                criteria.andEqualTo("coverTypeName", oldCoverTypeName);
                // 修改封面类型名称
                coverTypeMapper.updateByPrimaryKeySelective(coverType);
                // 根据oldCoverTypeName修改封面的coverTypeName
                coverMapper.updateByExampleSelective(coverItem, example);
            }
        }
        return ResultUtil.success();
    }

    /**
     * 添加、修改封面
     *
     * @param cover
     * @return
     * @throws Exception
     */
    @Override
    public Object modifyCover(Cover cover) throws Exception {
        if (cover.getName() != null && CheckCoverName(cover.getName())) {
            return ResultUtil.error(ResultEnum.ERROR_COVERNAME_USED);
        }
        if (cover.getId() == null) {
            /*
            添加
             */
            coverMapper.insertSelective(cover);
        } else {
            /*
            编辑
             */
            coverMapper.updateByPrimaryKeySelective(cover);
        }
        return ResultUtil.success();
    }

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除封面类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Object deleteCoverType(String id) throws Exception {
        List<String> ids = Arrays.asList(id.split(",")); // 当前需要删除的封面类型id
        /*
        根据需要删除的id查询对应的封面类型名称
         */
        Example example = new Example(CoverType.class); // 动态sql
        example.selectProperties("name"); // 指定查询字段
        example.setDistinct(true); // 去重
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids); // 设置查询条件
        List<CoverType> coverTypes = coverTypeMapper.selectByExample(example);
        /*
        将查询出的封面类型名称封装到List集合中去
         */
        List<String> coverTypeNames = new ArrayList<>();
        for(CoverType item : coverTypes ) {
            coverTypeNames.add(item.getName());
        }
        /*
        根据封装的封面类型名称集合修改封面信息中coverTypeName为""
         */
        example = new Example(Cover.class);
        criteria = example.createCriteria();
        criteria.andIn("coverTypeName", coverTypeNames);
        Cover cover = new Cover();
        cover.setCoverTypeName("");
        coverMapper.updateByExampleSelective(cover, example);
        /*
        根据id删除封面类型
         */
        example = new Example(CoverType.class);
        criteria = example.createCriteria();
        criteria.andIn("id", ids);
        coverTypeMapper.deleteByExample(example);
        return ResultUtil.success();
    }

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除封面
     * 1） 级联删除七牛云文件
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Object deleteCover(String id) throws Exception {
        List<Cover> covers; // 需要删除的封面数据
        Example example = new Example(Cover.class); // 动态sql
        example.selectProperties("src"); // 指定查询字段
        Example.Criteria criteria = example.createCriteria(); // 设置查询条件
        if ("all".equals(id)) {
            /*
            删除所有封面信息
             */
            covers = coverMapper.selectByExample(example); // 查询所有封面信息的src,关联七牛云删除文件使用
            coverMapper.delete(new Cover()); // 删除所有数据
        } else {
            /*
            删除指定id封面信息
             */
            List<String> ids = Arrays.asList(id.split(",")); // 需要删除的封面信息id集合
            // // 查询指定id的封面信息的src,关联七牛云删除文件使用
            criteria.andIn("id", ids);
            covers = coverMapper.selectByExample(example);
            example = new Example(Cover.class);
            criteria = example.createCriteria();
            criteria.andIn("id", ids);
            // 根据id批量删除指定封面信息
            coverMapper.deleteByExample(example);
        }

        /*
        批量删除七牛云文件
         */
        String[] keys; // 字符串数组,存储待删除的封面名称（七牛云的key）
        String key; // key
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < covers.size(); i++) {
            key = covers.get(i).getSrc(); // http://pc9o5rve4.bkt.clouddn.com/xxx
            key = key.substring(key.lastIndexOf("/") + 1); // xxx
            temp.append(key).append(","); // xxx,xxx,xxx,
            // 500条数据做一次批量删除
            if ((i + 1) % 500 == 0) {
                key = temp.substring(0, temp.lastIndexOf(",")); // xxx,xxx,xxx
                keys = key.split(","); // {xxx,xxx,xxx}
                QNYUtil.deleteFiles(keys);
                temp = new StringBuffer();
            }
            // 剩余的数据做一次批量删除
            if ((i + 1) == covers.size() && temp.length() > 0) {
                key = temp.substring(0, temp.lastIndexOf(",")); // xxx, | xxx,xxx,
                keys = key.split(","); // {xxx} | {xxx,xxx}
                QNYUtil.deleteFiles(keys);
                temp = new StringBuffer();
            }
        }
        return ResultUtil.success();
    }

}
