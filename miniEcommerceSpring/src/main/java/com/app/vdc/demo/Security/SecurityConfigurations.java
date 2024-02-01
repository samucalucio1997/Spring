package com.app.vdc.demo.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// @EnableGlobalMethodSecurity(jsr250Enabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
        
        @Autowired
        private FilterToken filter;
       
        @Bean
        public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
            http.cors().and().csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeHttpRequests(authorize -> {
               try {
                   authorize
                       .antMatchers(HttpMethod.POST, "/home/cadastroUser").permitAll()
                       .antMatchers(HttpMethod.POST, "/home/login").permitAll()    
                       .anyRequest().authenticated();
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           });
        return http
        .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class)
        .build();
        }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        System.out.println("passou aqui");    
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ 
            System.out.println("BCryptpasswordEncoder trabalhando");
            return new BCryptPasswordEncoder();
    } 

}
