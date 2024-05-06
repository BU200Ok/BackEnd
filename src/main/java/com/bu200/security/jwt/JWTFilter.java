package com.bu200.security.jwt;

import com.bu200.security.dto.CustomUserDetails;
import com.bu200.security.entity.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("토큰 검사하러 왔다 from JWTFilter");
        System.out.println("토큰 : "+request.getHeader("Authentication"));
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")){
            System.out.println("token이 없거나, Bearer가 포함되어 있지 않습니다.");
            filterChain.doFilter(request,response);
            return;
        }
        String token = authorization.split(" ")[1];
        //토큰의 소멸되었는지 검사하기
        if(jwtUtil.isExpired(token)){
            System.out.println("token Expire 상태입니다.");
            filterChain.doFilter(request,response);
            return;
        }
        //여기까지 오면 토큰이 정상이다.
        //토큰에서 아이디와, 권한 획득
        System.out.println("토큰이 정상입니다.");
        String accountId = jwtUtil.getUsername(token);
        String accountRole = jwtUtil.getRole(token);

        User user = new User();
        user.setAccountId(accountId);
        user.setAccountRole(accountRole);
        user.setAccountPassword("tempPassword");
        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);
    }
}
