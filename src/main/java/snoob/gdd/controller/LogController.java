package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/page/{pageNumber}/{pageSize}")
    public Object page(@RequestBody Log log, @PathVariable Integer pageNumber, @PathVariable Integer pageSize) throws Exception {
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
