package com.study.springsecsection1.filter;

import static com.study.springsecsection1.constants.ApplicationConstants.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenGeneratorFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String secret = getEnvironment().getProperty(JWT_SECRET_KEY, JWT_SECRET_DEFAULT_VALUE);
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder().issuer("Easy Bank").subject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", getCommaSeparatedAuthorities(authentication))
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 30000000))
                    .signWith(secretKey).compact();
            response.setHeader(JWT_HEADER, jwt);
        }
        filterChain.doFilter(request, response);
    }

    private String getCommaSeparatedAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    /**
     * 이 메소드에서 false 값을 반환하면 이 필터가 실행됩니다.
     * 이 메소드가 true가 반환되면 이 필터는 호출되지 않는다.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user");
    }

}
