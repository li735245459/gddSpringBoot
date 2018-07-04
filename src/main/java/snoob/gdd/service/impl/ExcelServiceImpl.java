package snoob.gdd.service.impl;

import org.springframework.stereotype.Service;
import snoob.gdd.mapper.UserMapper;
import snoob.gdd.model.User;
import snoob.gdd.service.ExcelService;
import snoob.gdd.util.ResultUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Object export(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ResultUtil.success();
    }
}
