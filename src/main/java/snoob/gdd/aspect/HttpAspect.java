package snoob.gdd.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
/**
 * AOP将共有的逻辑从业务逻辑中抽离出来
 */
public class HttpAspect {

//    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
//
//    @Pointcut("execution(public * snoob.gdd.controller.DemoController.*(..))")
//    public void log(){}
//
//    @Before("log()")
//    public void doBefore(JoinPoint joinPoint){
//        logger.info("before---------------");
//        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        logger.info("url={}",request.getRequestURL());
//        logger.info("method={}",request.getMethod());
//        logger.info("ip={}",request.getRemoteAddr());
//        logger.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
//        logger.info("args={}",joinPoint.getArgs());
//    }
//
//    @After("log()")
//    public void doAfter(){
//       logger.info("after-----------------");
//    }
//
//    @AfterReturning(returning = "object" , pointcut = "log()")
//    public void doAfterReturning(Object object){
//        logger.info("afterReturning-----------------");
//        logger.info("response={}",object.toString());
//    }
}
