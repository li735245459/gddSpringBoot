package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.CoverType;
import snoob.gdd.service.CoverService;

import javax.annotation.Resource;

/**
 * 封面模块
 */
@RequestMapping("/cover")
@RestController
public class CoverController {

    @Resource
    private CoverService coverService;

    /**
     * 添加、编辑封面类型信息
     *
     * @param coverType
     * @return
     * @throws Exception
     */
    @PostMapping("/modify")
    public Object modify(@RequestBody CoverType coverType) throws Exception {
        return coverService.modify(coverType);
    }

    /**
     * 分页查询封面类型信息
     *
     * @param coverType
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/page/{pageNumber}/{pageSize}")
    public Object page(@RequestBody CoverType coverType,
                       @PathVariable(value = "pageNumber") Integer pageNumber,
                       @PathVariable(value = "pageSize") Integer pageSize
    ) throws Exception {
        return coverService.page(coverType, pageNumber, pageSize);
    }

    /**
     * 删除封面类型信息
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody String id) throws Exception {
        return coverService.delete(id);
    }
}
