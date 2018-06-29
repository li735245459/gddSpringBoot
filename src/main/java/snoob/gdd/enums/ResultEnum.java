package snoob.gdd.enums;

/**
 *
 */
public enum ResultEnum {
    SYSTEM_ERROR(-1, "系统错误"),
    //
    ERROR_JWT_ERROR(11,"jwt校验失败"),
    ERROR_LOGIN_VALIDATE(12,"邮箱或者密码错误"),
    ERROR_EMAIL_ONLY_VALIDATE(13,"邮箱已被注册"),
    ERROR_EMAIL_CODE(14,"验证码错误"),
    ERROR_EMAIL(15,"邮箱非法"),
    ERROR_PHONE(16,"手机号码已被使用"),
    ERROR_EMAIL_TYPE(17, "验证类型非法")
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
