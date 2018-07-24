package snoob.gdd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import snoob.gdd.mapper.LogMapper;
import snoob.gdd.model.Log;
import snoob.gdd.model.User;
import snoob.gdd.service.LogService;
import snoob.gdd.util.ResultUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Resource
    private LogMapper logMapper;

    /**
     * 分页查询日志
     *
     * @param log
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public Object page(Log log, Integer pageNumber, Integer pageSize) throws Exception {
        Example example = new Example(User.class);
        example.orderBy("createTime").desc();
        // 开启分页模式
        PageHelper.startPage(pageNumber, pageSize);
        List<Log> logs = logMapper.selectByExample(example);
        // 获取分页对象
        PageInfo page = new PageInfo(logs);
        return ResultUtil.success(page);
    }

    /**
     * 添加日志
     *
     * @param log
     * @return
     * @throws Exception
     */
    @Override
    public Object insert(Log log) throws Exception {
        logMapper.insertSelective(log);
        return ResultUtil.success();
    }

    /**
     * 根据id字符串（多个id以,分割,all为删除所有）批量删除日志
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        if ("all".equals(id)) {
            logMapper.delete(new Log());
        } else {
            List<String> ids = Arrays.asList(id.split(","));
            logMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }
}
