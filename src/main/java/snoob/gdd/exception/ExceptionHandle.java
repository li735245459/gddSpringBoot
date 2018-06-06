package snoob.gdd.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.model.Result;
import snoob.gdd.util.ResultUtil;

import java.text.MessageFormat;

/**
 * 全局Controller异常捕获模块: 捕获controller抛出的异常
 *  service接口需要抛出异常
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GddException) {
            GddException gddException = (GddException) e;
            /**
             * 将所有打印的日志统一打印到这里
             */
            // 入库操作
            return ResultUtil.error(gddException.getCode(), gddException.getMessage());
        } else {
            // 入库操作
            logger.debug(MessageFormat.format("【系统错误】{0}--", e.getMessage()));
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR.getCode(), ResultEnum.SYSTEM_ERROR.getMsg());
        }
    }
}
