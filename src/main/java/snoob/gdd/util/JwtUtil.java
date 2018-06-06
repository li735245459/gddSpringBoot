package snoob.gdd.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snoob.gdd.exception.GlobalExceptionHandle;

import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * jwt工具类,生成、解析token
 */
public class JwtUtil {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);
    private final static String iss = "gdd";
    private final static String sub = "gdd";
    private final static Integer exp = 1000 * 60 * 10; // 默认10分钟
    private final static String secret = "25385a3d-2394-413d-a920-03e0bf25b81b";

    private static SignatureAlgorithm signatureAlgorithm;
    private static byte[] apiKeySecretBytes;
    private static Key signingKey;

    static {
        signatureAlgorithm = SignatureAlgorithm.HS256;
        apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 创建token
     * Map<String, Object> claims = new HashMap<>();
     * claims.put("jti", "lixing"); //jwt的唯一身份标识主要用来作为一次性token,从而回避重放攻击: getId() and setId(String)
     * claims.put("aud", "lixing"); // 接收该JWT的一方: getAudience() and setAudience(String)
     * claims.put("nbf", "lixing"); // 在什么时间之前该jwt都是不可用的: getNotBefore() and setNotBefore(Date)
     *
     * @param claims
     * @return
     */
    public static String encodeJWT(Map<String, Object> claims) {
        claims.put("sub", sub); // 面向的用户: getSubject() and setSubject(String)
        claims.put("iss", iss); // 签发者: getIssuer() and setIssuer(String)
        Long nowTimeMillis = System.currentTimeMillis();
        claims.put("iat", new Date(nowTimeMillis)); // (签发时间): getIssuedAt() and setIssuedAt(Date)
        claims.put("exp", new Date(nowTimeMillis + exp)); // (过期时间): getExpiration() and setExpiration(Date)
        JwtBuilder builder = Jwts.builder().signWith(signatureAlgorithm, signingKey).setClaims(claims);
        return builder.compact();
    }

    /**
     * 刷新jwt
     *
     * @param claims
     * @return
     */
    public static String refreshJWT(Claims claims) {
        Long nowTimeMillis = System.currentTimeMillis();
        claims.put("iat", new Date(nowTimeMillis)); // (签发时间): getIssuedAt() and setIssuedAt(Date)
        claims.put("exp", new Date(nowTimeMillis + exp)); // (过期时间): getExpiration() and setExpiration(Date)
        JwtBuilder builder = Jwts.builder().signWith(signatureAlgorithm, signingKey).setClaims(claims);
        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param jwt
     */
    public static Claims decodeJWT(String jwt) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();

    }


    /**
     * 检查jwt:
     * 失败返回null
     * 成功返回jwt,当过期时间剩余1分钟是重新生成jwt
     *
     * @return
     */
    public static String checkJWT(String jwt) {
        Claims claims = decodeJWT(jwt);
        if (claims == null || !claims.getIssuer().equals(iss)) {
            // 错误的jwt,解析失败
            return null;
        } else {
            Long expTimeMillis = claims.getExpiration().getTime() / 1000;
            Date expDate = new Date(expTimeMillis);
            if (expDate.after(new Date())) {
                if (expTimeMillis - System.currentTimeMillis() < (1000 * 60)) {
                    // 重新生jwt
                    jwt = refreshJWT(claims);
                }
                // 有效
                return jwt;
            } else {
                // 过期
                return null;
            }
        }
    }

    public static void main(String[] args) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("secret", "secret");
//        claims.put("roles", "add;delete;update;select;");
//        claims.put("aud", "1234567");
//        String jwt = encodeJWT(claims);
//        System.out.println(jwt);

//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjM0NTY3Iiwic3ViIjoiZ2RkIiwicm9sZXMiOiJhZGQ7ZGVsZXRlO3VwZGF0ZTtzZWxlY3Q7IiwiaXNzIjoiZ2RkIiwic2VjcmV0Ijoic2VjcmV0IiwiZXhwIjoxNTI4MjA0Mjc0MDkzLCJpYXQiOjE1MjgyMDM2NzQwOTN9.YH90C4lBdXcmzNVITO0UJGIALQz-esJSR2Jq_WUxxoI";
//        System.out.print(checkJWT(jwt));
    }
}


