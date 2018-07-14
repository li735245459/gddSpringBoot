package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.mapper.CoverTypeMapper;
import snoob.gdd.model.CoverType;
import snoob.gdd.service.CoverService;
import snoob.gdd.util.ResultUtil;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class CoverServiceImpl implements CoverService {

    @Resource
    private CoverTypeMapper coverTypeMapper;

    /**
     * 查询封面类型信息
     *
     * @return
     */
    @Override
    public Object findCoverType() {
        List<CoverType> coverTypes = coverTypeMapper.selectAll();
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
    public Object modifyCoverType(CoverType coverType) throws Exception {
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
}
