package snoob.gdd.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
public class User {
    public User(){}
    public User(String email) {
        this.email = email;
    }

    /**
     * 编号,UUID字符串
     */
    @Id
    private String id;

    /**
     * 数据生成时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 头像地址链接
     */
    private String cover;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱(一个邮箱只能注册一个用户),找回密码使用
     */
    private String email;

    /**
     * 密码,MD5加密显示
     */
    private String password;

    /**
     * 性别(male:男,remale:女)
     */
    private String sex;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 自我介绍
     */
    private String introduce;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 创建jwt的密钥,注册时自动生成
     */
    private String secret;

    /**
     * 获取编号,UUID字符串
     *
     * @return id - 编号,UUID字符串
     */
    public String getId() {
        return id;
    }

    /**
     * 设置编号,UUID字符串
     *
     * @param id 编号,UUID字符串
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取数据生成时间
     *
     * @return create_time - 数据生成时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置数据生成时间
     *
     * @param createTime 数据生成时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取头像地址链接
     *
     * @return cover - 头像地址链接
     */
    public String getCover() {
        return cover;
    }

    /**
     * 设置头像地址链接
     *
     * @param cover 头像地址链接
     */
    public void setCover(String cover) {
        this.cover = cover;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱(一个邮箱只能注册一个用户),找回密码使用
     *
     * @return email - 邮箱(一个邮箱只能注册一个用户),找回密码使用
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱(一个邮箱只能注册一个用户),找回密码使用
     *
     * @param email 邮箱(一个邮箱只能注册一个用户),找回密码使用
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取密码,MD5加密显示
     *
     * @return password - 密码,MD5加密显示
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码,MD5加密显示
     *
     * @param password 密码,MD5加密显示
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别(male:男,remale:女)
     *
     * @return sex - 性别(male:男,remale:女)
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别(male:男,remale:女)
     *
     * @param sex 性别(male:男,remale:女)
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取爱好
     *
     * @return hobby - 爱好
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * 设置爱好
     *
     * @param hobby 爱好
     */
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    /**
     * 获取自我介绍
     *
     * @return introduce - 自我介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置自我介绍
     *
     * @param introduce 自我介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return area - 区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区
     *
     * @param area 区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取创建jwt的密钥,注册时自动生成
     *
     * @return secret - 创建jwt的密钥,注册时自动生成
     */
    public String getSecret() {
        return secret;
    }

    /**
     * 设置创建jwt的密钥,注册时自动生成
     *
     * @param secret 创建jwt的密钥,注册时自动生成
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }
}