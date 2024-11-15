package com.sawaljawab.SawalJawab.config;

import com.sawaljawab.SawalJawab.Security.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.awt.*;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");
        String token=null;
        String username=null;
        if (authHeader!=null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            username=jwtService.extractUserName(token);
        }
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            if (jwtService.validateToken(token,userDetails))
        }
    }
}
