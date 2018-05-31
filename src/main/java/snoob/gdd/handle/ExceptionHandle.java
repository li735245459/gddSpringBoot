package snoob.gdd.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import snoob.gdd.exception.GddException;
import snoob.gdd.model.Result;
import snoob.gdd.util.ResultUtil;

/**
 * 自定义全局异常处理
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GddException) {
            GddException gddException = (GddException) e;
            return ResultUtil.error(gddException.getCode(), gddException.getMessage());
        } else {
            logger.error("【系统错误】", e);
            return ResultUtil.error(-1, e.getMessage());
        }
    }
}
