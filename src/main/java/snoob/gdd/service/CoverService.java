package snoob.gdd.service;

import snoob.gdd.model.CoverType;
import snoob.gdd.model.Cover;

public interface CoverService {
    // 封面类型信息
    Object findCoverType() throws Exception;
    Object modifyCoverType(CoverType coverType) throws Exception;
    Object deleteCoverType(String id) throws Exception;
    // 封面信息
    Object pageCover(Cover cover, Integer pageNumber, Integer pageSize) throws Exception;
    Object modifyCover(Cover cover) throws Exception;
    Object deleteCover(String id) throws Exception;
}
