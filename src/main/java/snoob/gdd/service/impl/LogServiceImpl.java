package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.mapper.LogMapper;
import snoob.gdd.model.Log;
import snoob.gdd.service.LogService;
import snoob.gdd.util.ResultUtil;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    @Override
    public Object page(Log log, Integer pageNumber, Integer pageSize) {
        List<Log> logs = logMapper.select(new Log());
        return ResultUtil.success(logs);
    }

    @Override
    public Object insert(Log log) {
        logMapper.insertSelective(log);
        return ResultUtil.success();
    }

    @Override
    public Object delete(String id) throws Exception {
        if ("all".equals(id)) { // 删除所有
            logMapper.delete(new Log());
        } else { // 删除所选(批量)
            List<String> ids = Arrays.asList(id.split(","));
            logMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }
}
