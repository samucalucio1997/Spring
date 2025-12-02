package com.app.vdc.demo.Security;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.vdc.demo.repository.UserRepository;

@Component
public class FilterToken extends OncePerRequestFilter{

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String token="";
        var authorization = request.getHeader("Authorization");
        if(authorization != null){
            token = authorization.replace("Bearer ", "");
//            var subject = TokenUtil.getSubject(token);
            DecodedJWT jwt = TokenUtil.decodeAndVerifyIgnoringExpiration(token);
            String subject = jwt.getSubject();
            var usuario = this.userRepository.findByUsername(subject);

            Iterator junks = usuario.getAuthorities().iterator();
            while (junks.hasNext()) {
                System.out.println(junks.next());
            }
            
            var authentication = new UsernamePasswordAuthenticationToken(usuario,
            null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

}
