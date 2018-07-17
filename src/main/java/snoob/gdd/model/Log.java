package snoob.gdd.model;

import snoob.gdd.util.UUIdGenIdUtil;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
import javax.persistence.*;

@Table(name = "log")
public class Log {
    /**
     * 编号
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
     * 请求链接
     */
    @Column(name = "req_url")
    private String reqUrl;

    /**
     * 请求方法
     */
    @Column(name = "req_method")
    private String reqMethod;

    /**
     * 请求参数
     */
    @Column(name = "req_args")
    private String reqArgs;

    /**
     * 控制层方法
     */
    @Column(name = "controller_method")
    private String controllerMethod;

    /**
     * 日志信息
     */
    private String msg;

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
     * 获取请求链接
     *
     * @return req_url - 请求链接
     */
    public String getReqUrl() {
        return reqUrl;
    }

    /**
     * 设置请求链接
     *
     * @param reqUrl 请求链接
     */
    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl == null ? null : reqUrl.trim();
    }

    /**
     * 获取请求方法
     *
     * @return req_method - 请求方法
     */
    public String getReqMethod() {
        return reqMethod;
    }

    /**
     * 设置请求方法
     *
     * @param reqMethod 请求方法
     */
    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod == null ? null : reqMethod.trim();
    }

    /**
     * 获取请求参数
     *
     * @return req_args - 请求参数
     */
    public String getReqArgs() {
        return reqArgs;
    }

    /**
     * 设置请求参数
     *
     * @param reqArgs 请求参数
     */
    public void setReqArgs(String reqArgs) {
        this.reqArgs = reqArgs == null ? null : reqArgs.trim();
    }

    /**
     * 获取控制层方法
     *
     * @return controller_method - 控制层方法
     */
    public String getControllerMethod() {
        return controllerMethod;
    }

    /**
     * 设置控制层方法
     *
     * @param controllerMethod 控制层方法
     */
    public void setControllerMethod(String controllerMethod) {
        this.controllerMethod = controllerMethod == null ? null : controllerMethod.trim();
    }

    /**
     * 获取日志信息
     *
     * @return msg - 日志信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置日志信息
     *
     * @param msg 日志信息
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}