package snoob.gdd.model;

import snoob.gdd.util.UUIdGenIdUtil;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "goods")
public class Goods {
    /**
     * 编号,uuid
     */
    @Id
    @KeySql(genId = UUIdGenIdUtil.class)
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 外键编号
     */
    @Column(name = "goods_type_id")
    private String goodsTypeId;

    /**
     * 外键名称
     */
    @Column(name = "goods_type_name")
    private String goodsTypeName;

    /**
     * 名称
     */
    private String name;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 价格
     */
    private Double price;

    /**
     * 促销价格
     */
    @Column(name = "promotion_price")
    private Double promotionPrice;

    /**
     * 获取编号,uuid
     *
     * @return id - 编号,uuid
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号,uuid
     *
     * @param id 编号,uuid
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取外键编号
     *
     * @return goods_type_id - 外键编号
     */
    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    /**
     * 设置外键编号
     *
     * @param goodsTypeId 外键编号
     */
    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId == null ? null : goodsTypeId.trim();
    }

    /**
     * 获取外键名称
     *
     * @return goods_type_name - 外键名称
     */
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    /**
     * 设置外键名称
     *
     * @param goodsTypeName 外键名称
     */
    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName == null ? null : goodsTypeName.trim();
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取介绍
     *
     * @return introduce - 介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置介绍
     *
     * @param introduce 介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * 获取价格
     *
     * @return price - 价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取促销价格
     *
     * @return promotion_price - 促销价格
     */
    public Double getPromotionPrice() {
        return promotionPrice;
    }

    /**
     * 设置促销价格
     *
     * @param promotionPrice 促销价格
     */
    public void setPromotionPrice(Double promotionPrice) {
        this.promotionPrice = promotionPrice;
    }
}