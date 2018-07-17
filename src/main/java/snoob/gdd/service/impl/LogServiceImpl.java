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
     * 分页查询
     *
     * @param log
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Object page(Log log, Integer pageNumber, Integer pageSize) {
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
     */
    @Override
    public Object insert(Log log) {
        logMapper.insertSelective(log);
        return ResultUtil.success();
    }

    /**
     * 删除
     * id = "3b2ebfa1-ed59-4091-a800-aef6e867f1a1" 表示单一删除
     * id = "3b2ebfa1-ed59-4091-a800-aef6e867f1a1,3b2ebfa1-ed59-4091-a800-aef6e867f1a2" 表示批量删除
     * id = "all" 表示清空
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Object delete(String id) throws Exception {
        if ("all".equals(id)) {
            /*删除所有*/
            logMapper.delete(new Log());
        } else {
            /*删除所选(批量)*/
            List<String> ids = Arrays.asList(id.split(","));
            logMapper.customDelete(ids);
        }
        return ResultUtil.success();
    }
}
