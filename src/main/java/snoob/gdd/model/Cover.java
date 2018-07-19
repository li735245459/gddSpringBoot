package snoob.gdd.model;

import snoob.gdd.util.UUIdGenIdUtil;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cover")
public class Cover {
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
     * 封面类型名称
     */
    @Column(name = "cover_type_name")
    private String coverTypeName;

    /**
     * 是否激活,0屏蔽,1激活
     */
    @Column(name = "isActive")
    private Integer isactive;

    /**
     * 名称
     */
    private String name;

    /**
     * 下载地址
     */
    private String src;

    /**
     * 说明
     */
    private String introduce;

    /**
     * 外链地址
     */
    private String href;

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
     * 获取封面类型名称
     *
     * @return cover_type_name - 封面类型名称
     */
    public String getCoverTypeName() {
        return coverTypeName;
    }

    /**
     * 设置封面类型名称
     *
     * @param coverTypeName 封面类型名称
     */
    public void setCoverTypeName(String coverTypeName) {
        this.coverTypeName = coverTypeName == null ? null : coverTypeName.trim();
    }

    /**
     * 获取是否激活,0屏蔽,1激活
     *
     * @return isActive - 是否激活,0屏蔽,1激活
     */
    public Integer getIsactive() {
        return isactive;
    }

    /**
     * 设置是否激活,0屏蔽,1激活
     *
     * @param isactive 是否激活,0屏蔽,1激活
     */
    public void setIsactive(Integer isactive) {
        this.isactive = isactive;
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
     * 获取下载地址
     *
     * @return src - 下载地址
     */
    public String getSrc() {
        return src;
    }

    /**
     * 设置下载地址
     *
     * @param src 下载地址
     */
    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }

    /**
     * 获取说明
     *
     * @return introduce - 说明
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置说明
     *
     * @param introduce 说明
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * 获取外链地址
     *
     * @return href - 外链地址
     */
    public String getHref() {
        return href;
    }

    /**
     * 设置外链地址
     *
     * @param href 外链地址
     */
    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }
}