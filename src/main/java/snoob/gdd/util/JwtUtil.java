package snoob.gdd.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

/**
 * jwt工具类,生成、解析token
 */
public class JwtUtil {
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";

    /**
     * 创建token
     *
     * @param id
     * @param issuer
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String issuer, String subject, long ttlMillis) {

        // 签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 当前时间戳
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // 添加token过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //构建JWT序列化到一个紧凑,URL-safe字符串
        return builder.compact();

    }

    /**
     * 解析token
     *
     * @param jwt
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_SECRET))
                .parseClaimsJws(jwt)
                .getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        return claims;
    }
}


