package snoob.gdd.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "color")
public class Color {
    /**
     * 编号
     */
    @Id
    private String id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 颜色名称：棕色
     */
    private String name;

    /**
     * 关联goods_type表的id
     */
    @Column(name = "type_id")
    private String typeId;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(String id) {
        this.id = id;
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
     * 获取颜色名称：棕色
     *
     * @return name - 颜色名称：棕色
     */
    public String getName() {
        return name;
    }

    /**
     * 设置颜色名称：棕色
     *
     * @param name 颜色名称：棕色
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取关联goods_type表的id
     *
     * @return type_id - 关联goods_type表的id
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置关联goods_type表的id
     *
     * @param typeId 关联goods_type表的id
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}