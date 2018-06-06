package snoob.gdd.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.model.Result;
import snoob.gdd.util.ResultUtil;

/**
 * Controller模块异常捕获处理,返回Result
 */
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandle {

    //@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return ResultUtil.error(globalException.getCode(), globalException.getMessage());
        } else {
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg());
        }
    }
}
