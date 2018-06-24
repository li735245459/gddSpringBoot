package snoob.gdd.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snoob.gdd.model.Log;
import snoob.gdd.service.LogService;

import javax.annotation.Resource;

/**
 * 日志模块
 */
@RequestMapping("/log")
@RestController
public class LogController {

    @Resource
    private LogService logService;

    @PostMapping("/page")
    public Object page(@RequestBody Log log, Integer pageNumber, Integer pageSize) throws Exception {
        return logService.page(log, pageNumber, pageSize);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody String id) throws Exception {
        return logService.delete(id);
    }
}
