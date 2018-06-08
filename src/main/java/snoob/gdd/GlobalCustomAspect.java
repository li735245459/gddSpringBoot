package snoob.gdd;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.util.JwtUtil;
import snoob.gdd.util.LoggerUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;


/**
 * 拦截http请求
 */
@Aspect
@Component
public class GlobalCustomAspect {
    /**
     * 定义一个切点pointCut
     * 切入的位置: 所有controller模块下的所有的方法
     */
    @Pointcut("execution(public * snoob.gdd.controller.*.*(..))")
    public void pointCut() {
    }

    /**
     * 前置通知。在某连接点之前执行的通知，但这个通知不能阻止连接点之前的执行流程（除非它抛出一个异常）
     *
     * @param joinPoint
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        LoggerUtil.info("===============HttpAspect before===============");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/gdd/user/login") ||
                requestURI.contains("/gdd/user/register") ||
                requestURI.contains("/gdd/user/forgetPassword") ||
                requestURI.contains("/gdd/user/modifyPassword") ||
                requestURI.contains("/gdd/email/send") ||
                requestURI.contains("/gdd/email/checkEmailCode")) {
            // 不需要校验jwt
        } else {
            // 校验jwt
            String authorization = request.getHeader("Authorization");
            if (authorization == null || !authorization.startsWith("Bearer")) {
                throw new GlobalCustomException(ResultEnum.ERROR_JWT_ERROR);
            } else {
                String jwt = authorization.substring(6);
                Claims claims = JwtUtil.decodeJWT(jwt);
                if (claims == null) {
                    throw new GlobalCustomException(ResultEnum.ERROR_JWT_ERROR);
                } else {
                    request.setAttribute("userId", claims.getAudience());
                }
            }

        }
    }

//    /**
//     * 后置通知。在某连接点正常完成后执行的通知，通常在一个匹配的方法返回的时候执行
//     * @param object
//     */
//    @AfterReturning(returning = "object", pointcut = "pointCut()")
//    public void doAfterReturning(Object object) {
//        LoggerUtil.info("===============HttpAspect doAfterReturning===============");
//        LoggerUtil.info(MessageFormat.format("response={0}", object.toString()));
//    }


//    /**
//     * 最终通知。当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）
//     */
//    @After("pointCut()")
//    public void doAfter() {
//        LoggerUtil.info("===============HttpAspect after===============");
//    }

    /**
     * 异常通知。在方法抛出异常退出时执行的通知
     */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LoggerUtil.info("===============【系统错误】===============");
        LoggerUtil.info(MessageFormat.format("错误信息:{0}", e.getMessage()));
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LoggerUtil.info(MessageFormat.format("ip地址-->{0}", request.getRemoteAddr()));
        LoggerUtil.info(MessageFormat.format("请求链接-->{0}", request.getRequestURL()));
        LoggerUtil.info(MessageFormat.format("请求参数-->{0}", joinPoint.getArgs()));
        LoggerUtil.info(MessageFormat.format("请求类型-->{0}", request.getMethod()));
        LoggerUtil.info(MessageFormat.format("执行方法-->{0}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()));
    }
}
