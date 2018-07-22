package snoob.gdd.enums;

public enum ResultEnum {
    SYSTEM_ERROR(-1, "系统错误"),
    ERROR_JWT(1000, "JWT非法"),
    ERROR_EMAIL_OR_PASSWORD(100100, "邮箱或密码错误"),
    ERROR_EMAIL_OR_CODE(100101, "邮箱或验证码错误"),
    ERROR_EMAIL_USED(100102, "邮箱已被注册"),
    ERROR_EMAIL_ILLEGAL(100103, "邮箱非法"),
    ERROR_EMAIL_CODE_MODULE_ILLEGAL(100104, "邮箱验证码模块类型非法"),
    ERROR_PHONE_USED(100200, "手机号码已被使用"),
    ERROR_IMPORT_FILE_NULL(100300, "文件读取失败"),
    ERROR_QNY_UPFILE(100301, "上传文件失败(七牛云)"),
    ERROR_QNY_DELETEFILE(100302, "删除文件失败(七牛云)"),
    ERROR_QNY_MODIFYFILE(100303, "修改文件失败(七牛云)"),
    ERROR_IMPORT_FILE_DATA_NULL(100301, "Excel文件没有数据"),
    ERROR_COVERNAME_USED(100400, "封面名称已存在"),
    ERROR_COVER_FORMAT_ILLEGAL(100401, "封面格式错误"),
    ERROR_COVERTYPENAME_USED(100402, "封面类型名称已存在")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

//    public static void main(String[] args) {
//        for (ResultEnum e : ResultEnum.values()) {
//            System.out.println(e.getCode());
//        }
//    }

}
