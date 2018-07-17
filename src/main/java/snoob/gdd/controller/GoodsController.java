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
    @PostMapping("/modifyGoodsType")
    public Object modify(@RequestBody GoodsType goodsType) throws Exception {
        return goodsService.modifyGoodsType(goodsType);
    }

    /**
     * 分页查询商品类型信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/findGoodsType")
    public Object findGoodsType() throws Exception {
        return goodsService.findGoodsType();
    }

    /**
     * 删除商品类型信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteGoodsType")
    public Object delete(@RequestBody String id) throws Exception {
        return goodsService.deleteGoodsType(id);
    }
}
