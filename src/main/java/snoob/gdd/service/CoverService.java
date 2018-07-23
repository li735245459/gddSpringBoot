package snoob.gdd.service;

import snoob.gdd.model.CoverType;
import snoob.gdd.model.Cover;

/**
 * 操作封面类型、封面
 */
public interface CoverService {
    /**
     * 查询封面类型
     *
     * @return
     * @throws Exception
     */
    Object selectCoverType() throws Exception;

    /**
     * 添加、编辑封面类型
     *
     * @param coverType
     * @return
     * @throws Exception
     */
    Object modifyCoverType(CoverType coverType) throws Exception;

    /**
     * 根据ID删除封面类型
     *
     * @param id
     * @return
     * @throws Exception
     */
    Object deleteCoverType(String id) throws Exception;

    /**
     * 分页查询封面
     *
     * @param cover
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    Object pageCover(Cover cover, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 添加、修改封面
     *
     * @param cover
     * @return
     * @throws Exception
     */
    Object modifyCover(Cover cover) throws Exception;

    /**
     * 根据id删除封面
     *
     * @param id
     * @return
     * @throws Exception
     */
    Object deleteCover(String id) throws Exception;
}
