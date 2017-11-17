package com.demo.common.utils;

import com.demo.common.constant.JwtConstant;
import io.jsonwebtoken.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Date;

/**
 * jwt工具类，用于生成token
 * @author Tommy Huang
 * @email tommy.huang@demo.com
 * @date 2017/11/6 21:07
 */
public class JwtUtils {
    /**
     *
     * @return
     * @throws IOException
     */
    public static SecretKey generalKey() throws IOException {
        String stringKey = JwtConstant.SECRETKEY;
        byte[] encodedKey = JwtUtils.decode(stringKey);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }
    /**
     * 解码
     * @param str
     * @return string
     */
    public static byte[] decode(String str) throws IOException {
        byte[] bt = null;
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        bt = decoder.decodeBuffer(str);

        return bt;
    }


    /**
     * 生成jwtWebToken
     * @param id 用户ID
     * @param subject 用户信息
     * @return
     * @throws Exception
     */
    public static String createJwtWebToken(String id, String subject) throws IOException {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (JwtConstant.JWT_WEB_EXPIRETIME >= 0) {
            long expMillis = nowMillis + JwtConstant.JWT_WEB_EXPIRETIME;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 生成jwtRestful token
     * @param id 用户ID
     * @param subject 用户信息
     * @param deviceFlag 设备唯一标识（如：imei）
     * @return
     */
    public static String createJwtRestfulToken(String id, String subject,String deviceFlag) throws IOException{
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now).claim("deviceFlag",deviceFlag)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);
        if (JwtConstant.JWT_RESTFUL_EXPIRETIME>= 0) {
            long expMillis = nowMillis + JwtConstant.JWT_RESTFUL_EXPIRETIME;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     * @param jwt
     * @return  获取JWT时候可能会抛出异常，异常信息可能会是过期了
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws  IOException{
        SecretKey key = generalKey();
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
    public static void main(String[] args) throws IOException{
        System.out.println(JwtUtils.createJwtRestfulToken("1","21343","43545"));
        System.out.println(JwtUtils.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTEwNTM0NTcxLCJkZXZpY2VGbGFnIjoiNDM1NDUiLCJzdWIiOiIyMTM0MyIsImV4cCI6MTUxMTgzMDU3MX0.pYeCwTSsP76ILhvDtudhPyNQ_62FGnlAV88tC3LuBPQ").getId());
    }
}
