package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import snoob.gdd.model.Cover;
import snoob.gdd.model.CoverType;
import snoob.gdd.service.CoverService;
import snoob.gdd.util.ResultUtil;

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
    @GetMapping("/selectCoverType")
    public Object selectCoverType() throws Exception {
        return coverService.selectCoverType();
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

    /**
     * 分页查询封面信息
     *
     * @param cover
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/pageCover/{pageNumber}/{pageSize}")
    public Object pageCover(@RequestBody Cover cover,
                            @PathVariable(value = "pageNumber") Integer pageNumber,
                            @PathVariable(value = "pageSize") Integer pageSize
    ) throws Exception {
        return coverService.pageCover(cover, pageNumber, pageSize);
    }

    /**
     * 修改、添加封面信息 @RequestBody Cover cover
     *
     * Content type 'multipart/form-data;boundary=----WebKitFormBoundaryaJPqL4I6GEtD7a8H;charset=UTF-8' not supported
     *
     * @param cover
     * @return
     * @throws Exception
     */
    @PostMapping("/modifyCover")
    public Object modifyCover(@RequestParam String coverTypeName, @RequestPart("files") MultipartFile files) throws Exception {
//        return coverService.modifyCover(cover);
        return ResultUtil.success();

    }

    /**
     * 删除封面信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteCover")
    public Object deleteCover(@RequestBody String id) throws Exception {
        return coverService.deleteCover(id);
    }
}
