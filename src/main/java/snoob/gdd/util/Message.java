package snoob.gdd.util;

/**
 * 操作记录
 */
public class Message {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 内容
     */
    private String content;
    /**
     * 需要返回的数据
     */
    private Object obj;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
