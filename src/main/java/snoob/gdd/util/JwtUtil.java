package snoob.gdd.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import snoob.gdd.exception.ExceptionHandle;

import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类,生成、解析token
 */
public class JwtUtil {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);
    private final static String iss = "gdd";
    private final static String sub = "gdd";
    private final static Integer exp = 1000 * 60;
    private final static String secret = "25385a3d-2394-413d-a920-03e0bf25b81b";

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
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        claims.put("sub", sub); // 面向的用户: getSubject() and setSubject(String)
        claims.put("iss", iss); // 签发者: getIssuer() and setIssuer(String)
        Calendar nowTime = Calendar.getInstance();
        claims.put("iat", nowTime.getTime()); // 签发时间: getIssuedAt() and setIssuedAt(Date)
        nowTime.add(Calendar.MINUTE, 1);
        claims.put("exp", nowTime.getTime()); // Unix时间戳(过期时间): getExpiration() and setExpiration(Date)
        JwtBuilder builder = Jwts.builder().signWith(signatureAlgorithm, signingKey).setClaims(claims);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * 解析token
     *
     * @param jwt
     */
    public static Claims decodeJWT(String jwt) {
        try {
            // 解析成功
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            // 解析失败
            logger.debug(MessageFormat.format("【token解析失败:】{0}", e.getMessage()));
            return null;
        }
    }

    /**
     * 检查jwt
     * false：错误的jwt,解析失败,过期
     * true：有效
     *
     * @return
     */
    public static Boolean checkJWT(String jwt) {
        Claims claims = decodeJWT(jwt);
        if (claims == null || !claims.getIssuer().equals(iss)) {
            // 错误的jwt,解析失败
            return false;
        } else {
            Date expDate = claims.getExpiration();
            System.out.println(expDate.getTime());
            System.out.println(expDate);
            if (expDate.after(new Date())) {
                // 有效
                return true;
            } else {
                // 过期
                return false;
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
//        System.out.println("----");
        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjM0NTY3Iiwic3ViIjoiZ2RkIiwicm9sZXMiOiJhZGQ7ZGVsZXRlO3VwZGF0ZTtzZWxlY3Q7IiwiaXNzIjoiZ2RkIiwic2VjcmV0Ijoic2VjcmV0IiwiZXhwIjoxNTI4MTg2NDg0NzY3LCJpYXQiOjE1MjgxODY0MjQ3Njd9.7oX49PRmEA-pw8ZwwzV-9zD2ZjTIfFLliD7dmoK0zsM";
        System.out.println(checkJWT(jwt));
    }
}


