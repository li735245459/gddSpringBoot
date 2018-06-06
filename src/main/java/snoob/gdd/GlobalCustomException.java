package snoob.gdd;

import snoob.gdd.enums.ResultEnum;

/**
 * 自定义全局异常
 * spring 对于 RuntimeException 异常会进行事务回滚
 */
public class GlobalCustomException extends RuntimeException{

    private Integer code;

    public GlobalCustomException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
