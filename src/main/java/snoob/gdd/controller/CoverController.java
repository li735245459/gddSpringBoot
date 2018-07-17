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
     * 查询封面类型信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/findCoverType")
    public Object findCoverType() throws Exception {
        return coverService.findCoverType();
    }

    /**
     * 添加、编辑封面类型信息
     *
     * @param coverType
     * @return
     * @throws Exception
     */
    @PostMapping("/modifyCoverType")
    public Object modify(@RequestBody CoverType coverType) throws Exception {
        return coverService.modifyCoverType(coverType);
    }

    /**
     * 删除封面类型信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteCoverType")
    public Object delete(@RequestBody String id) throws Exception {
        return coverService.deleteCoverType(id);
    }
}
