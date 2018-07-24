package snoob.gdd.service;

import snoob.gdd.model.CoverType;
import snoob.gdd.model.Cover;

/**
 * 封面类型、封面接口
 */
public interface CoverService {

    /**
     * 检查封面类型名称是否合法
     * @return
     * @throws Exception
     */
    Boolean CheckCoverTypeName(String coverTypeName) throws Exception;

    /**
     * 检查封面名称是否合法
     * @return
     * @throws Exception
     */
    Boolean CheckCoverName(String coverName) throws Exception;

    /**
     * 查询封面类型（前端以Tree的形式展示封面分类信息，后端查询按照nodeLevel降序排列、createTime升序排列）
     * @return
     * @throws Exception
     */
    Object selectCoverType() throws Exception;

    /**
     * 分页查询封面
     * @param cover
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    Object pageCover(Cover cover, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 添加、编辑封面类型
     * @param coverType
     * @return
     * @throws Exception
     */
    Object modifyCoverType(CoverType coverType) throws Exception;

    /**
     * 添加、修改封面
     * @param cover
     * @return
     * @throws Exception
     */
    Object modifyCover(Cover cover) throws Exception;

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除封面类型
     * @param id
     * @return
     * @throws Exception
     */
    Object deleteCoverType(String id) throws Exception;

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除封面
     * 级联删除七牛云文件
     * @param id
     * @return
     * @throws Exception
     */
    Object deleteCover(String id) throws Exception;
}
