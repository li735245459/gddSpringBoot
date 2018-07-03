package snoob.gdd.model;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import snoob.gdd.util.UUIdGenId;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
@ExcelTarget("user")
public class User {
    /**
     * 编号,UUID字符串
     */
    @Id
    @KeySql(genId = UUIdGenId.class)
    @Excel(name = "编号", orderNum = "1")
    private String id;

    /**
     * 数据生成时间
     */
    @Column(name = "create_time")
    @Excel(name = "注册时间", orderNum = "2", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 头像地址链接
     */
    @Excel(name = "头像", orderNum = "3", imageType = 1)
    private String cover;

    /**
     * 姓名
     */
    @Excel(name = "姓名", orderNum = "4")
    private String name;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码", orderNum = "5")
    private String phone;

    /**
     * 邮箱(一个邮箱只能注册一个用户),找回密码使用
     */
    @Excel(name = "邮箱", orderNum = "6")
    private String email;

    /**
     * 密码,MD5加密显示
     */
    private String password;

    /**
     * 性别(male:男,female:女)
     */
    private String sex;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 备注
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
     * 登陆时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 登陆ip
     */
    @Column(name = "login_ip")
    private String loginIp;

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
        this.id = id == null ? null : id.trim();
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
        this.cover = cover == null ? null : cover.trim();
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
        this.name = name == null ? null : name.trim();
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
        this.phone = phone == null ? null : phone.trim();
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
        this.email = email == null ? null : email.trim();
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
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取性别(male:男,female:女)
     *
     * @return sex - 性别(male:男,female:女)
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别(male:男,female:女)
     *
     * @param sex 性别(male:男,female:女)
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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
        this.hobby = hobby == null ? null : hobby.trim();
    }

    /**
     * 获取备注
     *
     * @return introduce - 备注
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置备注
     *
     * @param introduce 备注
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
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
        this.province = province == null ? null : province.trim();
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
        this.city = city == null ? null : city.trim();
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
        this.area = area == null ? null : area.trim();
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
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取登陆时间
     *
     * @return login_time - 登陆时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登陆时间
     *
     * @param loginTime 登陆时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取登陆ip
     *
     * @return login_ip - 登陆ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登陆ip
     *
     * @param loginIp 登陆ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", introduce='" + introduce + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                '}';
    }
}