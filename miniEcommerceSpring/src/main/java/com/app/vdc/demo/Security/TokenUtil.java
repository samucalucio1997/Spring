package com.app.vdc.demo.Security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


import org.springframework.security.core.Authentication;

import com.app.vdc.demo.Model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;



public class TokenUtil {
    

    
    public static String encodeToken(User user){
        String token = JWT.create()
                       .withIssuer("Auth")
                       .withSubject(user.getUsername())
                       .withClaim("id", user.getId())
                       .withExpiresAt(LocalDateTime.now()
                       .plusDays(1)
                       .toInstant(ZoneOffset.of("-03:00")))
                       .sign(Algorithm.HMAC256("grazinads"));
        return new AuthToken(token).getToken();
    }

    //Ira autenticar o token recebido   
    public static Authentication decodeToken(String Auth){//String correspondente a requisição passada no cabeçalho
             
        return null;
    }

    public static String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256("grazinads"))
                   .withIssuer("Auth")
                   .build().verify(token).getSubject();
    }    
}
