package snoob.gdd.service;

import snoob.gdd.model.CoverType;

public interface CoverService {
    Object modify(CoverType coverType) throws Exception;
    Object page(CoverType coverType, Integer pageNumber, Integer pageSize) throws Exception;
    Object delete(String id) throws Exception;
}
