package snoob.gdd.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "log")
public class Log {
    @Id
    private String id;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "req_url")
    private String reqUrl;

    @Column(name = "req_method")
    private String reqMethod;

    @Column(name = "req_args")
    private String reqArgs;

    @Column(name = "controller_method")
    private String controllerMethod;

    private String msg;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return req_url
     */
    public String getReqUrl() {
        return reqUrl;
    }

    /**
     * @param reqUrl
     */
    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl == null ? null : reqUrl.trim();
    }

    /**
     * @return req_method
     */
    public String getReqMethod() {
        return reqMethod;
    }

    /**
     * @param reqMethod
     */
    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod == null ? null : reqMethod.trim();
    }

    /**
     * @return req_args
     */
    public String getReqArgs() {
        return reqArgs;
    }

    /**
     * @param reqArgs
     */
    public void setReqArgs(String reqArgs) {
        this.reqArgs = reqArgs == null ? null : reqArgs.trim();
    }

    /**
     * @return controller_method
     */
    public String getControllerMethod() {
        return controllerMethod;
    }

    /**
     * @param controllerMethod
     */
    public void setControllerMethod(String controllerMethod) {
        this.controllerMethod = controllerMethod == null ? null : controllerMethod.trim();
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}