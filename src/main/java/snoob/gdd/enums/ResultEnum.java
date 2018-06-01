package snoob.gdd.enums;

/**
 *
 */
public enum ResultEnum {
    //SYSTEM_ERROR(-1, "系统错误"),
    //SUCCESS(0, "请求成功"),
    //ERROR(1, "请求失败"),
    //
    ERROR_EMAIL_SENDER(11,"邮件发送失败"),
    ERROR_LOGIN_VALIDATE(12,"邮箱或者密码错误"),
    ERROR_EMAIL_ONLY_VALIDATE(13,"邮箱已被注册"),
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
