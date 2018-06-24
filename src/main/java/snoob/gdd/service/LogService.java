package snoob.gdd.service;

import snoob.gdd.model.Log;

public interface LogService {
    Object page(Log log, Integer pageNumber, Integer pageSize) throws Exception;

    Object insert(Log log) throws Exception;

    Object delete(String id) throws Exception;
}
