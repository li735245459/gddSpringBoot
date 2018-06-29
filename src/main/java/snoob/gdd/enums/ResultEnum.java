package snoob.gdd.enums;

/**
 *
 */
public enum ResultEnum {
    SYSTEM_ERROR(-1, "系统错误"),
    ERROR_JWT(1000,"JWT校验失败"),
    ERROR_EMAIL_OR_PASSWORD(100100,"邮箱或密码错误"),
    ERROR_EMAIL_OR_CODE(100101,"邮箱或验证码错误"),
    ERROR_EMAIL_USED(100102,"邮箱已被注册"),
    ERROR_EMAIL_ILLEGAL(100103,"邮箱非法"),
    ERROR_EMAIL_CODE_MODULE_ILLEGAL(100104, "邮箱验证码模块类型非法"),
    ERROR_PHONE_USED(100105,"手机号码已被使用"),
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
