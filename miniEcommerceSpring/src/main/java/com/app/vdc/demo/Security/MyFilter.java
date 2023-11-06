package com.app.vdc.demo.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyFilter extends OncePerRequestFilter{

    @Override
    protected void doFilterInternal(HttpServletRequest arg0,
     HttpServletResponse arg1, FilterChain arg2)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        if(arg0.getHeader("Authorization")!=null){
            String Auth = arg0.getHeader("Authorization");
            //Verificação de token para a autenticação
            if(TokenUtil.decodeToken(Auth)!=null){
                // se o meu token for válido passo a requisição para frente, confirmando a autenticação
                SecurityContextHolder.getContext().
                setAuthentication(TokenUtil.decodeToken(Auth));
            }else{
                //O token não foi reconhecido 
                System.out.println("erro no token");
                ErrorUser err = new ErrorUser(401, "Erro no token");
                arg1.setStatus(err.getStatus());
                arg1.setContentType("Aplication/Json");
                ObjectMapper mapper = new ObjectMapper();
                arg1.getWriter().println(mapper.writeValueAsString(err));
                arg1.getWriter().flush(); return;
            }
        }
        //passa a requisição para frente
        arg2.doFilter(arg0, arg1);    
    }   
    
}
