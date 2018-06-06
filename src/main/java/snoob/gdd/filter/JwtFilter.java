package snoob.gdd.filter;

import io.jsonwebtoken.Claims;
import org.springframework.web.filter.GenericFilterBean;
import snoob.gdd.enums.ResultEnum;
import snoob.gdd.exception.GlobalException;
import snoob.gdd.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT拦截器
 * JWT验证主要是通过拦截器验证,所以我们需要添加一个拦截器来验证请求头中是否含有后台颁发的token
 */
public class JwtFilter extends GenericFilterBean {

    private String doNotFilterUrl = "user/login;user/register;user/forgetPassword;user/modifyPassword;" +
            "email/send;email/checkEmailCode";

    /**
     * 自定义拦截器
     *
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String requestURI = request.getRequestURI();
        if (doNotFilterUrl.contains(requestURI) || "OPTIONS".equals(request.getMethod())) {
            /**
             * 无需拦截
             */
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(req, res);
        } else {
            /**
             * 拦截
             */
            final String authHeader = request.getHeader("authorization"); //获取请求头中authorization信息
            if (authHeader == null || !authHeader.startsWith("bearer")) {
                throw new GlobalException(ResultEnum.ERROR_LOGIN_VALIDATE);
            } else {
                final String token = authHeader.substring(5);
                final Claims claims = JwtUtil.decodeJWT(token);
                if (claims == null) {
                    throw new GlobalException(ResultEnum.ERROR_LOGIN_VALIDATE);
                }
                request.setAttribute("claims", claims);
                chain.doFilter(req, res);
            }
        }
    }
}