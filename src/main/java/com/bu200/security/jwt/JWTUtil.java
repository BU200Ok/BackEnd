package com.bu200.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
    private final SecretKey secretKey;    //yml에 있는 secretKey임

    public JWTUtil(@Value("${spring.jwt.secret}")String secret){    //생성자가 yml 의 key를 받아와서 시크릿 키를 만들었다.
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    private Claims getInfo(String token){
        //verifyWith 는 시크릿 키를 갖고 우리 서버에서 생성된 토큰이 맞는지 검사한다.
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }
    public Integer getUserCode(String token) {
        return getInfo(token).get("accountCode",Integer.class);
    }
    public String getEmail(String token) {
        return getInfo(token).get("accountEmail",String.class);
    }
    public String getName(String token){
        return getInfo(token).get("accountName",String.class);
    }
    public String getUsername(String token){
        return getInfo(token).get("accountId",String.class);
    }
    public String getRole(String token){
        return getInfo(token).get("accountRole",String.class);
    }
    public Boolean isExpired(String token){
        try{
            Boolean test = getInfo(token).getExpiration().before(new Date());
            return test;
        } catch (ExpiredJwtException e){
            return true;
        }


    }

    //JWT 를 발급하는 메서드이다. 추가하고 싶은 정보를 추가하자.
    public String createJwt(Integer accountcode, String username, String role, String email, String name, Long expiredMs){
        System.out.println("만들어진 토큰의 시간 : "+new Date(System.currentTimeMillis()));
        System.out.println("토큰이 만료될 시간 : "+new Date(System.currentTimeMillis() + expiredMs));
        System.out.println("email? : " + email);
        return Jwts.builder()
                .claim("accountCode", accountcode)
                .claim("accountId",username)
                .claim("accountRole",role)
                .claim("accountEmail",email)
                .claim("accountName",name)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }


}
