package snoob.gdd.controller;

import org.springframework.web.bind.annotation.*;
import snoob.gdd.model.Goods;
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
     * 查询商品类型信息
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

    /**
     *分页查询商品信息
     *
     * @param goods
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/pageGoods/{pageNumber}/{pageSize}")
    public Object pageCover(@RequestBody Goods goods,
                            @PathVariable(value = "pageNumber") Integer pageNumber,
                            @PathVariable(value = "pageSize") Integer pageSize
    ) throws Exception {
        return goodsService.pageGoods(goods, pageNumber, pageSize);
    }

    /**
     * 修改、添加商品信息
     *
     * @param goods
     * @return
     * @throws Exception
     */
    @PostMapping("/modifyGoods")
    public Object modifyCover(@RequestBody Goods goods) throws Exception {
        return goodsService.modifyGoods(goods);
    }

    /**
     * 删除商品信息
     *
     * @param id
     * @return
     */
    @PostMapping("/deleteGoods")
    public Object deleteCover(@RequestBody String id) throws Exception {
        return goodsService.deleteGoods(id);
    }
}
