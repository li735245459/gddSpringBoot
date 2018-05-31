package snoob.gdd.util;

import org.springframework.stereotype.Component;

/**
 * 操作记录
 */
@Component
public class Message {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 状态
     */
    private String status;
    /**
     * 内容
     */
    private String message;
    /**
     * 需要返回的数据
     */
    private Object obj;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
