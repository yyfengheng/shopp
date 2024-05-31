package com.caihongjia.common.util;

import com.caihongjia.pojo.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // 创建jwt令牌
    public static String createJwt(String key,UserVO claims){
        String jwt = Jwts.builder()
                .claim("data",claims)// 自定义内容, 将用户信息存入其中
                .signWith(SignatureAlgorithm.HS256,key)// 使用HS256加密算法,caihongjia为自定义密钥
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 10000))// 设置有效期, 1小时
                .compact();
        return jwt;
    }

    // 解析jwt令牌
    public static Claims parseJwt(String key,String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key) // 指定签名密钥
                .parseClaimsJws(token) // 解析令牌
                .getBody();
        return claims;
    }
}
