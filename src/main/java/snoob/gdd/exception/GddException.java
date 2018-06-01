package snoob.gdd.exception;

import snoob.gdd.enums.ResultEnum;

/**
 * 自定义异常
 * 抛出RuntimeException异常时spring会进行事务回滚
 */
public class GddException extends RuntimeException{
    private Integer code;

    public GddException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
