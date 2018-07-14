package snoob.gdd.service;

import snoob.gdd.model.CoverType;

public interface CoverService {
    Object findCoverType() throws Exception;
    Object modifyCoverType(CoverType coverType) throws Exception;
    Object deleteCoverType(String id) throws Exception;
}
