package com.tr.resume.interceptor;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
public class TokenInterceptor implements HandlerInterceptor {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            return false;
        }

        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        try {
            // 2. 构建解析器
            JwtParser parser = Jwts.parser()
                    .verifyWith(key)
                    .build();
            Jws<Claims> jws = parser.parseSignedClaims(token);
            Claims claims = jws.getPayload();
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Expiration: " + claims.getExpiration());
        } catch (JwtException e) {
            System.err.println("Token验证失败: " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
