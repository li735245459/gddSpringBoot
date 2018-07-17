package snoob.gdd.model;

import snoob.gdd.util.UUIdGenIdUtil;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
     * 商品类型名称
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
     * 是否激活,0屏蔽1激活
     */
    @Column(name = "isActive")
    private Integer isactive;

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
     * 获取商品类型名称
     *
     * @return goods_type_name - 商品类型名称
     */
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    /**
     * 设置商品类型名称
     *
     * @param goodsTypeName 商品类型名称
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

    /**
     * 获取是否激活,0屏蔽1激活
     *
     * @return isActive - 是否激活,0屏蔽1激活
     */
    public Integer getIsactive() {
        return isactive;
    }

    /**
     * 设置是否激活,0屏蔽1激活
     *
     * @param isactive 是否激活,0屏蔽1激活
     */
    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
    }
}