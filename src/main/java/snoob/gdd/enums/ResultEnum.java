package snoob.gdd.enums;

/**
 *
 */
public enum ResultEnum {
    SYSTEM_ERROR(-1, "系统错误"),
    SUCCESS(0, "成功"),
    REGISTER_FAILED(1,"注册失败"),
    EMAIL_REFISTERED(2,"该邮箱已被注册"),
    LOFIN_FAILED(3,"邮箱或者密码错误"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
