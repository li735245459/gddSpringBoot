package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.model.CoverType;
import snoob.gdd.service.CoverService;

@Service
public class CoverServiceImpl implements CoverService {
    /**
     * 编辑、添加封面类型信息
     *
     * @param coverType
     * @return
     * @throws Exception
     */
    @Override
    public Object modify(CoverType coverType) throws Exception {
        return null;
    }

    /**
     * 分页查询封面类型信息
     * @param coverType
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object page(CoverType coverType, Integer pageNumber, Integer pageSize) throws Exception {
        return null;
    }

    /**
     * 删除封面类型信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        return null;
    }
}
