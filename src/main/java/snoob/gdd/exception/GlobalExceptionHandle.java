package snoob.gdd.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.model.Result;
import snoob.gdd.util.LoggerUtil;
import snoob.gdd.util.ResultUtil;

import java.text.MessageFormat;

/**
 * Controller模块全局异常捕获处理
 */
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandle {

    //@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            LoggerUtil.info(MessageFormat.format("【错误代码:{0}】--{1}", globalException.getCode(), globalException.getMessage()));
            return ResultUtil.error(globalException.getCode(), globalException.getMessage());
        } else {
            LoggerUtil.info(MessageFormat.format("【系统错误:-1】--{0}", e.getMessage()));
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), e.getMessage());
        }
    }
}
