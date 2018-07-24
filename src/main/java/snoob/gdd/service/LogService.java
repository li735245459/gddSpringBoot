package snoob.gdd.service;

import snoob.gdd.model.Log;

public interface LogService {
    /**
     * 分页查询日志
     * @param log
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    Object page(Log log, Integer pageNumber, Integer pageSize) throws Exception;

    /**
     * 添加日志
     * @param log
     * @return
     * @throws Exception
     */
    Object insert(Log log) throws Exception;

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除日志
     * @param id
     * @return
     * @throws Exception
     */
    Object delete(String id) throws Exception;
}
