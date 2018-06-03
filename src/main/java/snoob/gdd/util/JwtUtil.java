package snoob.gdd.util;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;

import javax.xml.bind.DatatypeConverter;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Map;

/**
 * jwt工具类,生成、解析token
 */
public class JwtUtil {
    public static final String JWT_SECRET = "hong1mu2zhi3ruan4jian5";

    /**
     * 创建token
     *
     * @param claims Map<String, Object> claims = new HashMap<>();
     *               claims.put("secret", "lixing");
     *               claims.put("jti", "lixing"); //JWT ID (jti): getId() and setId(String)
     *               claims.put("iat", new Date(System.currentTimeMillis())); // Issued At (iat): getIssuedAt() and setIssuedAt(Date)
     *               claims.put("exp", new Date(System.currentTimeMillis() + 60)); // Expiration (exp): getExpiration() and setExpiration(Date)
     *               claims.put("sub", "lixing"); // Subject (sub): getSubject() and setSubject(String)
     *               claims.put("iss", "lixing"); // Issuer (iss): getIssuer() and setIssuer(String)
     *               claims.put("aud", "lixing"); // Audience (aud): getAudience() and setAudience(String)
     *               //map.put("nbf", "lixing"); // Not Before (nbf): getNotBefore() and setNotBefore(Date)
     * @return
     */
    public static String encodeJWT(Map<String, Object> claims) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(claims.get("secret").toString());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().signWith(signatureAlgorithm, signingKey).setClaims(claims);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * 解析token
     * 当前端传入的jwt无效时解析会报异常,具体异常请看文档
     *
     * @param jwt
     */
    public static Claims decodeJWT(String jwt, String secret) {
//        System.out.println(Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject().equals("Joe"));
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
    }

    public static void main(String[] args) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("secret", "lixing");
//        claims.put("jti", "lixing"); //JWT ID (jti): getId() and setId(String)
//        claims.put("iat", new Date(System.currentTimeMillis())); // Issued At (iat): getIssuedAt() and setIssuedAt(Date)
//        claims.put("exp", new Date(System.currentTimeMillis() + 1000*60)); // Expiration (exp): getExpiration() and setExpiration(Date)
//        claims.put("sub", "lixing"); // Subject (sub): getSubject() and setSubject(String)
//        claims.put("iss", "lixing"); // Issuer (iss): getIssuer() and setIssuer(String)
//        claims.put("aud", "lixing"); // Audience (aud): getAudience() and setAudience(String)
//        //map.put("nbf", "lixing"); // Not Before (nbf): getNotBefore() and setNotBefore(Date)
//        String jwt = encodeJWT(claims);
//        System.out.println(jwt);
//        System.out.println(decodeJWT(jwt, "lixing"));
//        System.out.println(decodeJWT(jwt, "lixing").getIssuedAt().getTime());
//        System.out.println(decodeJWT(jwt, "lixing").getExpiration().getTime());
//        System.out.println(System.currentTimeMillis());
    }
}


