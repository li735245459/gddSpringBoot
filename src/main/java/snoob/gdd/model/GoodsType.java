package snoob.gdd.model;

import snoob.gdd.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "goods_type")
public class GoodsType {
    /**
     * 编号
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;

    /**
     * 父级编号,"0"表示根节点
     */
    @Column(name = "p_id")
    private String pId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 节点级别,0表示根节点,1表示一级子节点
     */
    @Column(name = "node_level")
    private Integer nodeLevel;

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
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取父级编号,"0"表示根节点
     *
     * @return p_id - 父级编号,"0"表示根节点
     */
    public String getpId() {
        return pId;
    }

    /**
     * 设置父级编号,"0"表示根节点
     *
     * @param pId 父级编号,"0"表示根节点
     */
    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
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
     * 获取类别名称
     *
     * @return name - 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类别名称
     *
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取节点级别,0表示根节点,1表示一级子节点
     *
     * @return node_level - 节点级别,0表示根节点,1表示一级子节点
     */
    public Integer getNodeLevel() {
        return nodeLevel;
    }

    /**
     * 设置节点级别,0表示根节点,1表示一级子节点
     *
     * @param nodeLevel 节点级别,0表示根节点,1表示一级子节点
     */
    public void setNodeLevel(Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }
}