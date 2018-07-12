package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.GoodsType;
import snoob.gdd.service.GoodsService;

import javax.annotation.Resource;

/**
 * 商品模块
 */
@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    /**
     * 添加、编辑商品类型信息
     *
     * @param goodsType
     * @return
     * @throws Exception
     */
    @PostMapping("/modify")
    public Object modify(@RequestBody GoodsType goodsType) throws Exception {
        return goodsService.modify(goodsType);
    }

    /**
     * 分页查询商品类型信息
     *
     * @param goodsType
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/page/{pageNumber}/{pageSize}")
    public Object page(@RequestBody GoodsType goodsType,
                       @PathVariable(value = "pageNumber") Integer pageNumber,
                       @PathVariable(value = "pageSize") Integer pageSize
    ) throws Exception {
        return goodsService.page(goodsType, pageNumber, pageSize);
    }

    /**
     * 删除商品类型信息
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody String id) throws Exception {
        return goodsService.delete(id);
    }
}
