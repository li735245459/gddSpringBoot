package snoob.gdd.model;

import org.springframework.stereotype.Component;

/**
 * 服务器统一响应格式
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
     * 返回的数据
     */
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
