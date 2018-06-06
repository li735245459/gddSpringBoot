package snoob.gdd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志打印工具类
 */
public class LoggerUtil {
    private final static Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    /**
     * 打印错误信息
     * @param errorMsg
     */
    public static void info(String errorMsg){
        logger.info(errorMsg);
        // 入库未完成
    }
}
