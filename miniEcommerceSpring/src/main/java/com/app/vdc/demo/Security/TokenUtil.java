package com.app.vdc.demo.Security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.app.vdc.demo.Model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {
    private static final String EMISSOR = "samupro";
    private static final String TOKEN_HEADER="Authorization ";
    private static final String TOKEN_KEY = "01234567890123456789012345678901";
    private static final long UM_SEGUNDO=1000;
    private static final long UM_MINUTO=60*UM_SEGUNDO;

    
    public static AuthToken encodeToken(User user){
        Key secretKey = Keys.hmacShaKeyFor(TOKEN_KEY.getBytes());
        String Jwt_Token = Jwts.builder()
                           .setSubject(user.getUsername())
                           .setIssuer(EMISSOR)
                           .setExpiration(new Date(System.currentTimeMillis() + UM_MINUTO))
                           .signWith(secretKey).compact();
        AuthToken nToken = new AuthToken(TOKEN_HEADER + Jwt_Token);
        return nToken;
    }

     //Ira autenticar o token recebido   
     public static Authentication decodeToken(String Auth){//String correspondente a requisição passada no cabeçalho
        
        Auth = Auth.replace(TOKEN_HEADER, "");// Retiro so o nome do header que não pertence ao token
        //A realização do parse do token
        Jws<Claims> jwsClaim = Jwts.parser()
        .setSigningKey(TOKEN_KEY.getBytes())
        .build().parseClaimsJws(Auth);
        String user = jwsClaim.getPayload().getSubject();
        String emissor = jwsClaim.getPayload().getIssuer();
        Date validate = jwsClaim.getPayload().getExpiration();
        
        if(user.length()>0 && emissor.equals(EMISSOR)&&validate
        .equals(new Date(System.currentTimeMillis()+UM_MINUTO))){
            return new UsernamePasswordAuthenticationToken("user", "Moscou", Collections.emptyList());
        }else{
            return null;
        }
     }    
}
