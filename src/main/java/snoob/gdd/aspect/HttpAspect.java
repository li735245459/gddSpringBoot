package snoob.gdd.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import snoob.gdd.exception.GlobalExceptionHandle;
import snoob.gdd.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;


/**
 * 拦截http请求
 */
@Aspect
@Component
public class HttpAspect {

    @Autowired
    private GlobalExceptionHandle globalExceptionHandle;

    /**
     * 定义一个切点pointCut
     * 切入的位置: 所有controller模块下的所有的方法
     */
    @Pointcut("execution(public * snoob.gdd.controller.*.*(..))")
    public void pointCut() {
    }

    /**
     * 执行之前
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LoggerUtil.info("===============HttpAspect before===============");
        LoggerUtil.info(MessageFormat.format("url={0}", request.getRequestURL()));
        LoggerUtil.info(MessageFormat.format("method={0}", request.getMethod()));
        LoggerUtil.info(MessageFormat.format("ip={0}", request.getRemoteAddr()));
        LoggerUtil.info(MessageFormat.format("class_method={0}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()));
        LoggerUtil.info(MessageFormat.format("args={0}", joinPoint.getArgs()));
    }

//    @Around("pointCut()")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
////        System.out.println("around---------------------------");
////        Object ob = null;
////        System.out.println(pjp.getSignature().getDeclaringType());
////        //通过pjp对象获取Signature对象，该对象封装了连接点的信息。比如通过getDeclaringType获取连接点所在类的  class对象
////        //getName获取连接点的名称即方法名。
////        pjp.proceed();
////        return ob;
//        return null;
//    }


    /**
     * 执行之后
     */
    @After("pointCut()")
    public void doAfter() {
        LoggerUtil.info("===============HttpAspect after===============");
    }

    /**
     * 执行后的返回值
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "pointCut()")
    public void doAfterReturning(Object object) {
        LoggerUtil.info("===============HttpAspect doAfterReturning===============");
        LoggerUtil.info(MessageFormat.format("response={0}", object.toString()));
    }
}
